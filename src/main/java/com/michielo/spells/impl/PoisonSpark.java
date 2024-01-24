package com.michielo.spells.impl;

import com.michielo.particles.ParticleBehavior;
import com.michielo.particles.ParticleColor;
import com.michielo.particles.ParticleExecutor;
import com.michielo.spells.Spell;
import com.michielo.util.Eyes;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;

public class PoisonSpark extends Spell {

    private final int hitrange;
    private final int range;
    private final int damage;

    public PoisonSpark(int cooldown, int hitrange, int range, int damage) {
        super("PoisonSpark", cooldown);
        this.hitrange = hitrange;
        this.range = range;
        this.damage = damage;
    }

    @Override
    public void use(Player player) {
        Location targetLoc = null;

        Entity targetEntity = Eyes.isLookingAtEntity(player);
        if (targetEntity != null) targetLoc = targetEntity.getLocation();

        if (targetLoc != null) {
            Block targetBlockExact = player.getTargetBlockExact(range);
            if (targetBlockExact == null) return;
            targetLoc = targetBlockExact.getLocation();
        }

        Collection<Entity> entityList = targetLoc.getWorld().getNearbyEntities(targetLoc, hitrange, hitrange, hitrange);
        for (Entity e : entityList) {
            if (e instanceof LivingEntity p) {
                double duration = (double) damage / 2;
                double amplifier = (double) damage / 5;
                if (amplifier < 1.0) amplifier = 1.0;
                p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, (int) (20 * duration), (int) amplifier));
            }
        }
        ParticleExecutor.spawnShape(ParticleBehavior.SPARK, ParticleColor.POISON, targetLoc);
    }

}
