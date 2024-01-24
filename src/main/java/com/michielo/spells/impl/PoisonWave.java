package com.michielo.spells.impl;

import com.michielo.Main;
import com.michielo.particles.ParticleBehavior;
import com.michielo.particles.ParticleColor;
import com.michielo.particles.ParticleExecutor;
import com.michielo.spells.Spell;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;

public class PoisonWave extends Spell {

    private int damage;
    private int hitrange;

    public PoisonWave(int cooldown, int damage, int hitrange) {
        super("PoisonWave", cooldown);
        this.damage = damage;
        this.hitrange = hitrange;
    }

    @Override
    public void use(Player player) {
        ParticleExecutor.spawnShape(ParticleBehavior.WAVE, ParticleColor.POISON, player.getEyeLocation());
        for (int i = 0; i < 15; i++) {
            int finalI = i;
            new BukkitRunnable() {
                @Override
                public void run() {
                    Location copyLoc = player.getEyeLocation().clone();
                    for (int m = 0; m < finalI; m++) {
                        copyLoc = copyLoc.clone().add(copyLoc.getDirection().multiply(2.5));
                    }
                    Collection<Entity> entityList = copyLoc.getWorld().getNearbyEntities(copyLoc, hitrange, hitrange, hitrange);
                    for (Entity e : entityList) {
                        if (e instanceof LivingEntity p) {
                            double duration = (double) damage / 2;
                            double amplifier = (double) damage / 5;
                            if (amplifier < 1.0) amplifier = 1.0;
                            p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, (int) (20 * duration), (int) amplifier));
                        }
                    }
                }
            }.runTaskLater(Main.getInstance(), (i * 3));
        }
    }
}
