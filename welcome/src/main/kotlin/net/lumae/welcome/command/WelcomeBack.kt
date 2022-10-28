package net.lumae.welcome.command

import me.clip.placeholderapi.PlaceholderAPI
import me.lucko.helper.Commands
import me.lucko.helper.Schedulers
import net.lumae.welcome.Welcome
import net.lumae.welcome.listeners.LoginListener
import net.md_5.bungee.api.ChatColor
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.player.AsyncPlayerChatEvent
import java.util.*

class WelcomeBack//Convert alternate color code & message

//Add the player to the rate limit list, and remove the player after 60 seconds
    () {

    companion object {
        val rateLimit = mutableListOf<UUID>()
        val color_char = '\u00A7'
    }


    init {
        Welcome.instance?.let {
            Commands.create()
                .assertPermission("welcome.welcomeback")
                .assertPlayer()
                .handler {
                    val players = LoginListener.players

                    if(players.size == 0) {
                        it.reply("&cNo players have joined")
                        return@handler
                    }

                    if(rateLimit.contains(it.sender().uniqueId)) {
                        it.reply("&cYou can only use this command once every minute")
                        return@handler
                    }

                    val playerNames = players.map { Bukkit.getOfflinePlayer(it).name }

                    val multiplePlayers = playerNames.size > 1

                    val message = "&7Welcome back, " +
                            playerNames.joinToString(limit = if(multiplePlayers) playerNames.size-1 else 1, separator = ", ", postfix = if(multiplePlayers) ", and " else "") +
                            " ${if(multiplePlayers) playerNames[playerNames.size-1] else ""}"

                    //Convert alternate color code & message
                    val convertedMessage = PlaceholderAPI.setPlaceholders(it.sender(), message)
                    simulateChatMessage(it.sender(), convertedMessage)

                    //Add the player to the rate limit list, and remove the player after 60 seconds
                    rateLimit.add(it.sender().uniqueId)
                    Timer().schedule(object : TimerTask() {
                        override fun run() {
                            rateLimit.remove(it.sender().uniqueId)
                        }
                    }, 60000)
                }.registerAndBind(it, "wb", "welcomeback")
        }
    }

    fun simulateChatMessage(p: Player, message: String) {
        val set = mutableSetOf<Player>()
        Bukkit.getOnlinePlayers().forEach { set.add(it) }
        Schedulers.async().call {
            val event = AsyncPlayerChatEvent(true, p, message, set)
            Bukkit.getPluginManager().callEvent(event)
        }
    }

    fun translateHexColorCodes(message: String): String {
        val hexPattern = "&#(\\w{5}[0-9a-f])".toRegex().toPattern()
        val buffer = StringBuffer()
        val matcher = hexPattern.matcher(message)
        matcher.let {
            while (it.find()) {
                val hex = it.group(1)
                val color = ChatColor.of("#${hex}").toString()
                it.appendReplacement(buffer, color)
            }
        }

        return ChatColor.translateAlternateColorCodes('&', matcher.appendTail(buffer).toString())
    }

    //Get player by name
    fun getPlayerByName(name: String): Player? {
        return Bukkit.getServer().getPlayer(name)
    }
}