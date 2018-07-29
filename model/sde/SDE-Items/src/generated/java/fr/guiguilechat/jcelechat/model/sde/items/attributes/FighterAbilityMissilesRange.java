package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Optimal Range
 */
public class FighterAbilityMissilesRange
    extends IntAttribute
{
    public final static FighterAbilityMissilesRange INSTANCE = new FighterAbilityMissilesRange();

    @Override
    public int getId() {
        return  2149;
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
        return "FighterAbilityMissilesRange";
    }
}
