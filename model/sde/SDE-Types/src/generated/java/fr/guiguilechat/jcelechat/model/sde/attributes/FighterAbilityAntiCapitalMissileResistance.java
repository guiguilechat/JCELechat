package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class FighterAbilityAntiCapitalMissileResistance
    extends IntAttribute
{
    public static final FighterAbilityAntiCapitalMissileResistance INSTANCE = new FighterAbilityAntiCapitalMissileResistance();

    @Override
    public int getId() {
        return  2244;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public double getDefaultValue() {
        return  0.1;
    }

    @Override
    public boolean getPublished() {
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "FighterAbilityAntiCapitalMissileResistance";
    }
}
