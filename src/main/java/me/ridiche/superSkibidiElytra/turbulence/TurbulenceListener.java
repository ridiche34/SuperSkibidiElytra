package me.ridiche.superSkibidiElytra.turbulence;

import com.destroystokyo.paper.event.player.PlayerElytraBoostEvent;
import io.papermc.paper.math.Rotation;
import me.ridiche.superSkibidiElytra.SuperSkibidiElytra;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;

public class TurbulenceListener implements Listener {
    private static final HashMap<Player, Firework> boosts = new HashMap<>();

    private static boolean isBoosting(Player plr) {
        Firework firework = boosts.get(plr);
        if (firework == null)
            return false;
        else {
            if (firework.isValid())
                return true;
            else { // firework is gone (boost ended)
                boosts.remove(plr);
                return false;
            }
        }
    }

    private static boolean shouldVibrate(PlayerMoveEvent event) {
        return isBoosting(event.getPlayer());
    }

    private static float vibration(PlayerMoveEvent event) {
        float intensity = 3f;
        // multiply intensity by distance traveled
        intensity *= (float) event.getTo().clone().subtract(event.getFrom()).length();
        return (SuperSkibidiElytra.randomFloat()*2-1)*intensity;
    }

    @EventHandler
    public static void onPlayerMove(PlayerMoveEvent event) {
        Player plr = event.getPlayer();
        if (plr.isGliding())
        {
            if (shouldVibrate(event)) {
                Rotation initalRotation = event.getTo().clone().getRotation();
                plr.setRotation(initalRotation.yaw()+vibration(event),
                        SkibidiMath.clamp(initalRotation.pitch(), -90, 90)+vibration(event));
            }
        }
    }

    @EventHandler
    public static void onFireworkBoost(PlayerElytraBoostEvent event) {
        boosts.put(event.getPlayer(), event.getFirework());
    }
}
