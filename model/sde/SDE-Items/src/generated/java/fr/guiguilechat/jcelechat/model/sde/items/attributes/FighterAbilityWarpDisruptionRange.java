package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Optimal Range
 */
public class FighterAbilityWarpDisruptionRange
    extends IntAttribute
{
    public final static FighterAbilityWarpDisruptionRange INSTANCE = new FighterAbilityWarpDisruptionRange();

    @Override
    public int getId() {
        return  2204;
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
        return "FighterAbilityWarpDisruptionRange";
    }
}
