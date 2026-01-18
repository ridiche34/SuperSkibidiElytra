package me.ridiche.superSkibidiElytra.temperature;

import com.destroystokyo.paper.event.server.ServerTickEndEvent;
import me.ridiche.superSkibidiElytra.SuperSkibidiElytra;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.damage.DamageSource;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class TemperatureListener implements Listener {
    private static boolean isHot(World world) {
        return world.getKey().equals(new NamespacedKey("minecraft", "the_nether"));
    }

    @EventHandler
    public static void onTick(ServerTickEndEvent event) {
        for(Player plr : Bukkit.getOnlinePlayers()) {
            if (plr.isGliding() && isHot(plr.getWorld())) {
                if (plr.getVelocity().lengthSquared() > 0.5f) {
                    // Hurt
                    plr.damage(2f, DamageSource.builder(DamageType.IN_FIRE).build());
                    plr.setFireTicks(80);
                    // Warning
                    plr.sendActionBar(Component.text(
                            "Flaming hot particles fly into your face"
                    ).color(NamedTextColor.RED));
                }
                if (plr.getVelocity().lengthSquared() > 0.35f) {
                    // Effects
                    plr.getWorld().spawnParticle(Particle.LAVA,
                            plr.getLocation().clone().add(plr.getVelocity().multiply(5/plr.getVelocity().length())),
                            5,
                            0.5,
                            0.5,
                            0.5
                    );
                }
            }
        }
    }

    @EventHandler
    public static void onPlayerDeath(PlayerDeathEvent event) {
        if (event.getDamageSource().getDamageType() == DamageType.IN_FIRE
                && event.getPlayer().isGliding()
                && isHot(event.getPlayer().getWorld())) {
            String whoDied = event.getPlayer().getName();
            event.deathMessage(
                    Component.text(
                            whoDied+ " flew too close to the sun, except that the sun is lava, or wait, actually... The particles are from the enviornment, they're in the other biomes too... Wait, no, "+whoDied+" flew too close to the... flying, uh... hot micro-suns..? You know what, maybe this isn't a good metaphor after all."
                    ));
        }
    }
}
