package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Optimal Range
 */
public class FighterAbilityStasisWebifierOptimalRange
    extends IntAttribute
{
    public static final FighterAbilityStasisWebifierOptimalRange INSTANCE = new FighterAbilityStasisWebifierOptimalRange();

    @Override
    public int getId() {
        return  2186;
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
        return "FighterAbilityStasisWebifierOptimalRange";
    }
}
