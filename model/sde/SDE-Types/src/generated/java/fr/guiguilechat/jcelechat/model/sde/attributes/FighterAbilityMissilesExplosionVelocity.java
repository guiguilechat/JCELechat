package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Explosion Velocity
 */
public class FighterAbilityMissilesExplosionVelocity
    extends IntAttribute
{
    public static final FighterAbilityMissilesExplosionVelocity INSTANCE = new FighterAbilityMissilesExplosionVelocity();

    @Override
    public int getId() {
        return  2126;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  0.0;
    }

    @Override
    public boolean getPublished() {
        return true;
    }

    @Override
    public boolean getStackable() {
        return false;
    }

    @Override
    public String toString() {
        return "FighterAbilityMissilesExplosionVelocity";
    }
}
