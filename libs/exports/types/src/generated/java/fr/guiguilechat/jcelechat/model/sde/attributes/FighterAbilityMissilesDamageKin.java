package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Kinetic Damage
 */
public class FighterAbilityMissilesDamageKin
    extends RealAttribute
{
    public static final FighterAbilityMissilesDamageKin INSTANCE = new FighterAbilityMissilesDamageKin();

    @Override
    public int getId() {
        return  2133;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
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
        return "FighterAbilityMissilesDamageKin";
    }
}
