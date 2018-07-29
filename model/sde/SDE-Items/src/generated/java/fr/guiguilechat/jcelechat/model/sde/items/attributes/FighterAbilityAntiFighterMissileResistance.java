package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * 
 */
public class FighterAbilityAntiFighterMissileResistance
    extends IntAttribute
{
    public final static FighterAbilityAntiFighterMissileResistance INSTANCE = new FighterAbilityAntiFighterMissileResistance();

    @Override
    public int getId() {
        return  2189;
    }

    @Override
    public int getCatId() {
        return  38;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public double getDefaultValue() {
        return  0.2;
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
