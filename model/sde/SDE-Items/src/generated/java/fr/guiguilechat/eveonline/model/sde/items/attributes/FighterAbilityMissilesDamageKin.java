package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Kinetic Damage
 */
public class FighterAbilityMissilesDamageKin
    extends IntAttribute
{
    public final static FighterAbilityMissilesDamageKin INSTANCE = new FighterAbilityMissilesDamageKin();

    @Override
    public int getId() {
        return  2133;
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
        return "FighterAbilityMissilesDamageKin";
    }
}
