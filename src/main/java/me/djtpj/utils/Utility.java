package me.djtpj.utils;

import org.bukkit.Bukkit;

import java.util.logging.Level;

public final class Utility {
     public static void bounceCommand() {
         Manager.plugin.getLogger().log(Level.WARNING, "Whoops! The console cannot use this command!");
     }
}
