package net.lumae.welcome.listeners

import me.lucko.helper.Events
import net.md_5.bungee.api.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerJoinEvent
import java.net.http.WebSocket.Listener
import java.util.*

class LoginListener : Listener {

    companion object {
        val players = mutableListOf<UUID>()
        val color_char = '\u00A7'
    }

    constructor() {
        Events.subscribe(PlayerJoinEvent::class.java).handler(::onJoin)
    }


    fun onJoin(e: PlayerJoinEvent) {
        //When a player joins, add them to the list of players using addPlayer
        addPlayer(e.player)

        //After a minute if the player is present, remove them from the list
        Timer().schedule(object : TimerTask() {
            override fun run() {
                removePlayer(e.player)
            }
        }, 60000)
    }

    fun addPlayer(p: Player) {
        players.add(p.uniqueId)
    }

    fun removePlayer(p: Player) {
        players.remove(p.uniqueId)
    }


}