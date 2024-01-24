package com.michielo.particles;

import com.michielo.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.scheduler.BukkitRunnable;

public class ParticleExecutor {

    public static void spawnShape(ParticleBehavior shape, ParticleColor color, Location location) {
        if (shape == ParticleBehavior.SPARK) {
            Particle.DustOptions dustOptions = new Particle.DustOptions(ParticleColorConverter.convertColor(color), 1);
            Location modifiedLocation = location.add(0.5, 0.5, 0.5);
            spawnParticleSpark(modifiedLocation, dustOptions);
        }
        if (shape == ParticleBehavior.WAVE) {
            Particle.DustOptions dustOptions = new Particle.DustOptions(ParticleColorConverter.convertColor(color), 1);
            spawnVerticalWave(location, 1, 150, dustOptions);
        }
    }

    private static void spawnParticleCircle(Location centerLocation, double radius, int particles, Particle.DustOptions dustOptions) {
        for (int i = 0; i < particles; i++) {
            double angle = 2 * Math.PI * i / particles;
            double x = centerLocation.getX() + radius * Math.cos(angle);
            double y = centerLocation.getY();
            double z = centerLocation.getZ() + radius * Math.sin(angle);

            Location particleLocation = new Location(centerLocation.getWorld(), x, y, z);
            centerLocation.getWorld().spawnParticle(Particle.REDSTONE, particleLocation, 1, dustOptions);
        }
    }

    private static void spawnParticleSpark(Location centerLocation, Particle.DustOptions dustOptions) {
        double ychange = 0.0;
        for (int i = 0; i < 7; i++) {
            ychange += 0.6;
            double finalYchange = ychange;
            new BukkitRunnable() {
                @Override
                public void run() {
                    Location loc = centerLocation.clone().add(0.0, finalYchange, 0.0);
                    loc.getWorld().spawnParticle(Particle.REDSTONE, loc, 50, 2.0, 0.0, 2.0, dustOptions);
                    Location loc2 = centerLocation.clone().add(0.0, finalYchange + 0.3, 0.0);
                    loc2.getWorld().spawnParticle(Particle.REDSTONE, loc2, 50, 2.0, 0.0, 2.0, dustOptions);
                }
            }.runTaskLater(Main.getInstance(), (i * 1));
        }

    }

    private static void spawnVerticalWave(final Location centerLocation, final int range, final int particles, Particle.DustOptions dustOptions) {
        for (int i = 0; i < 15; i++) {
            int finalI = i;
            new BukkitRunnable() {
                @Override
                public void run() {
                    Location copyLoc = centerLocation.clone();
                    for (int m = 0; m < finalI; m++) {
                        copyLoc = copyLoc.clone().add(copyLoc.getDirection().multiply(2.5));
                    }
                    copyLoc.getWorld().spawnParticle(Particle.REDSTONE, copyLoc, particles, range, range, range, dustOptions);
                }
            }.runTaskLater(Main.getInstance(), (i * 3));
        }
    }

}
