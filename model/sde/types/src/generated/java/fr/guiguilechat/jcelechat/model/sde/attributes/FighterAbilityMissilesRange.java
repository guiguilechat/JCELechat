package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Optimal Range
 */
public class FighterAbilityMissilesRange
    extends IntAttribute
{
    public static final FighterAbilityMissilesRange INSTANCE = new FighterAbilityMissilesRange();

    @Override
    public int getId() {
        return  2149;
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
        return "FighterAbilityMissilesRange";
    }
}
