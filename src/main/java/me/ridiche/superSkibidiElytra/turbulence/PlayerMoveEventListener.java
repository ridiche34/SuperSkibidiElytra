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
        double chanceEveryBlock = 1d/20;
        if (isBoosting(event.getPlayer()))
            chanceEveryBlock = 1d/7;
        double distanceTravelled = event.getTo().clone().subtract(event.getFrom()).length();

        return SuperSkibidiElytra.randomDouble() < 1-(pow(1-chanceEveryBlock, distanceTravelled));
    }

    private static Vector vibration(PlayerMoveEvent event) {
        double intensity = event.getPlayer().getVelocity().lengthSquared()/30;
        if (isBoosting(event.getPlayer()))
            intensity *= 2;
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

    private static boolean shouldVibrateDueToFirework(Vector movement) {
        double chanceEveryBlock = 1d/10;
        double distanceTravelled = movement.length();

        return true;
        //return SuperSkibidiElytra.randomDouble() < 1-(pow(1-chanceEveryBlock, distanceTravelled));
    }

    private static Vector fireworkVibration() {
        return RandomUnitVectorGenerator.randomUnitVector().multiply(0.1f);
    }

    @EventHandler
    public static void onFireworkBoost(PlayerElytraBoostEvent event) {
        boosts.put(event.getPlayer(), event.getFirework());
    }
}
