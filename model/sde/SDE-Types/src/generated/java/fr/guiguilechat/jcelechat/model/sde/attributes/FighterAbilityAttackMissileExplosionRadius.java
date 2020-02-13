package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Explosion Radius
 */
public class FighterAbilityAttackMissileExplosionRadius
    extends IntAttribute
{
    public static final FighterAbilityAttackMissileExplosionRadius INSTANCE = new FighterAbilityAttackMissileExplosionRadius();

    @Override
    public int getId() {
        return  2234;
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
        return "FighterAbilityAttackMissileExplosionRadius";
    }
}
