package me.austinfrg.superhealall.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;
import java.util.Objects;

public class HealAllCommand implements CommandExecutor {
    private final JavaPlugin plugin;

    public HealAllCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission(Objects.requireNonNull(plugin.getConfig().getString("heal-perm-required")))) {
            final Collection<? extends Player> list = Bukkit.getOnlinePlayers();
            final Player[] players = list.toArray(new Player[0]);
            int amount = Integer.parseInt(args[0]);
            for (final Player allplayers : players) {
                AttributeInstance checkmaxhp = allplayers.getAttribute(Attribute.GENERIC_MAX_HEALTH);
                int memhp = (int) allplayers.getHealth();
                int maxhp = (int) Objects.requireNonNull(checkmaxhp).getValue();
                if (amount > maxhp-memhp) {
                    allplayers.setHealth(maxhp);
                }
                else if (memhp < maxhp) {
                    allplayers.setHealth(Double.parseDouble(String.valueOf(memhp + amount)));
                }
            }
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("healed-msg"))));
            return true;
        }
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("no-perm-msg"))));
        return true;
    }
}