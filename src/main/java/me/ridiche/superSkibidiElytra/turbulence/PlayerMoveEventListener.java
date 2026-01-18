package me.ridiche.superSkibidiElytra.turbulence;

import com.destroystokyo.paper.event.player.PlayerElytraBoostEvent;
import io.papermc.paper.event.entity.EntityMoveEvent;
import me.ridiche.superSkibidiElytra.SuperSkibidiElytra;
import org.apache.commons.lang3.RandomUtils;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.pow;
import static org.apache.commons.lang3.RandomUtils.nextDouble;

public class PlayerMoveEventListener implements Listener {
    private static HashMap<Player, Firework> boosts = new HashMap<Player, Firework>();

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

    private static Vector vibration(PlayerMoveEvent event) {
        double intensity = 0.05f;
        // multiply intensity by distance traveled
        intensity *= event.getTo().clone().subtract(event.getFrom()).length();
        return RandomUnitVectorGenerator.randomUnitVector().multiply(intensity);
    }

    @EventHandler
    public static void onPlayerMove(PlayerMoveEvent event) {
        Player plr = event.getPlayer();
        if (plr.isGliding())
        {
            if (shouldVibrate(event)) {
                Vector prevVelocity = plr.getVelocity();
                plr.teleport(
                        event.getTo().clone().setDirection(
                                event.getTo().getDirection().add(
                                        vibration(event)
                                )));
                plr.setVelocity(prevVelocity);
            }
        }
    }

    @EventHandler
    public static void onFireworkBoost(PlayerElytraBoostEvent event) {
        boosts.put(event.getPlayer(), event.getFirework());
    }
}
