package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Optimal Range
 */
public class FighterAbilityECMRangeOptimal
    extends IntAttribute
{
    public final static FighterAbilityECMRangeOptimal INSTANCE = new FighterAbilityECMRangeOptimal();

    @Override
    public int getId() {
        return  2221;
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
}
