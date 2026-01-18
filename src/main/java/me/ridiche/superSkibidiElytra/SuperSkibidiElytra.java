package me.ridiche.superSkibidiElytra;

import me.ridiche.superSkibidiElytra.temperature.TemperatureListener;
import me.ridiche.superSkibidiElytra.turbulence.TurbulenceListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

import static org.apache.commons.lang3.RandomUtils.nextDouble;

public final class SuperSkibidiElytra extends JavaPlugin {

    public static Logger LOGGER;

    public static double randomDouble() {
        return nextDouble() / Double.MAX_VALUE;
    }

    @Override
    public void onEnable() {
        LOGGER = this.getLogger();
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new TurbulenceListener(), this);
        pluginManager.registerEvents(new TemperatureListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
