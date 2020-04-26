package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Optimal Range
 */
public class FighterAbilityECMRangeOptimal
    extends IntAttribute
{
    public static final FighterAbilityECMRangeOptimal INSTANCE = new FighterAbilityECMRangeOptimal();

    @Override
    public int getId() {
        return  2221;
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
        return "FighterAbilityECMRangeOptimal";
    }
}
