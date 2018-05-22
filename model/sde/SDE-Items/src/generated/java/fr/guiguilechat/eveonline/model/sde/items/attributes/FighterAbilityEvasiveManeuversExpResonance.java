package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Shield Explosive Damage Resistance
 */
public class FighterAbilityEvasiveManeuversExpResonance
    extends DoubleAttribute
{
    public final static FighterAbilityEvasiveManeuversExpResonance INSTANCE = new FighterAbilityEvasiveManeuversExpResonance();

    @Override
    public int getId() {
        return  2121;
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
        return  1.0;
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
        return "FighterAbilityEvasiveManeuversExpResonance";
    }
}
