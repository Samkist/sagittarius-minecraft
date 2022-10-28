package net.lumae.welcome

import me.lucko.helper.plugin.ExtendedJavaPlugin
import net.lumae.welcome.command.WelcomeBack
import net.lumae.welcome.listeners.LoginListener

class Welcome : ExtendedJavaPlugin() {
    companion object {
        var instance: Welcome? = null
            private set
    }


    override fun enable() {
        instance = this
        LoginListener()
        WelcomeBack()
    }
}