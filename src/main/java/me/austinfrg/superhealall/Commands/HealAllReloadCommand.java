package me.austinfrg.superhealall.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class HealAllReloadCommand implements CommandExecutor {
    private final JavaPlugin plugin;

    public HealAllReloadCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission(Objects.requireNonNull(plugin.getConfig().getString("reload-perm-required")))) {
            plugin.saveDefaultConfig();
            plugin.reloadConfig();
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("reloaded-msg"))));
            return true;
        }
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("no-perm-msg"))));
        return true;
    }
}