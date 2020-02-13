package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Explosion Velocity
 */
public class FighterAbilityAttackMissileExplosionVelocity
    extends IntAttribute
{
    public static final FighterAbilityAttackMissileExplosionVelocity INSTANCE = new FighterAbilityAttackMissileExplosionVelocity();

    @Override
    public int getId() {
        return  2235;
    }

    @Override
    public int getCatId() {
        return  34;
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
        return "FighterAbilityAttackMissileExplosionVelocity";
    }
}
