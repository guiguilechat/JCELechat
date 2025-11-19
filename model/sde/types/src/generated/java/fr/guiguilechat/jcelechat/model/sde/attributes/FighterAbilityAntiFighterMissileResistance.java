package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class FighterAbilityAntiFighterMissileResistance
    extends IntAttribute
{
    public static final FighterAbilityAntiFighterMissileResistance INSTANCE = new FighterAbilityAntiFighterMissileResistance();

    @Override
    public int getId() {
        return  2189;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public Number getDefaultValue() {
        return  0.05;
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
        return "FighterAbilityAntiFighterMissileResistance";
    }
}
