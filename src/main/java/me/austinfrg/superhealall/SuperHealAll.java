package me.austinfrg.superhealall;

import me.austinfrg.superhealall.Commands.HealAllCommand;
import me.austinfrg.superhealall.Commands.HealAllReloadCommand;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class SuperHealAll extends JavaPlugin implements CommandExecutor {

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        Objects.requireNonNull(this.getCommand("healall")).setExecutor(new HealAllCommand(this));
        Objects.requireNonNull(this.getCommand("healallreload")).setExecutor(new HealAllReloadCommand(this));
        int pluginId = 8771;
        new Metrics(this, pluginId);
    }

    @Override
    public void onDisable() {
    }
}