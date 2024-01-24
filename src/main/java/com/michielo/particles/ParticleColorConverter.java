package com.michielo.particles;

import org.bukkit.Color;

public class ParticleColorConverter {

    public static Color convertColor(ParticleColor color) {
        if (color == ParticleColor.POISON) {
            return Color.fromBGR(0, 51, 0);
        }
        return null;
    }

}
