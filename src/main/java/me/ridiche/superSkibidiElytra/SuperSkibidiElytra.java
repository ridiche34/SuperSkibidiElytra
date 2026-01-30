package me.ridiche.superSkibidiElytra;

import me.ridiche.superSkibidiElytra.equip.EquipMenu;
import me.ridiche.superSkibidiElytra.equip.PlayerMenuManager;
import me.ridiche.superSkibidiElytra.temperature.TemperatureListener;
import me.ridiche.superSkibidiElytra.turbulence.TurbulenceListener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.logging.Logger;

import static org.apache.commons.lang3.RandomUtils.nextDouble;
import static org.apache.commons.lang3.RandomUtils.nextFloat;

public final class SuperSkibidiElytra extends JavaPlugin implements Listener {

    public static Logger LOGGER;
    private static final HashMap<Player, PlayerMenuManager> menuManagers = new HashMap<>();

    public static double randomDouble() {
        return nextDouble() / Double.MAX_VALUE;
    }
    public static float randomFloat() {
        return nextFloat() / Float.MAX_VALUE;
    }


    @Override
    public void onEnable() {
        LOGGER = this.getLogger();
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(this, this);
        pluginManager.registerEvents(new TurbulenceListener(), this);
        pluginManager.registerEvents(new TemperatureListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player plr = event.getPlayer();
        PlayerMenuManager menuManager = new PlayerMenuManager(plr);
        menuManager.openMenu(new EquipMenu(menuManager));
        menuManagers.put(plr, menuManager);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        menuManagers.remove(event.getPlayer());
    }
}
