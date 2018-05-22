package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Rate of fire
 */
public class FighterAbilityMissilesDuration
    extends IntAttribute
{
    public final static FighterAbilityMissilesDuration INSTANCE = new FighterAbilityMissilesDuration();

    @Override
    public int getId() {
        return  2182;
    }

    @Override
    public int getCatId() {
        return  34;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
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
        return "FighterAbilityMissilesDuration";
    }
}
