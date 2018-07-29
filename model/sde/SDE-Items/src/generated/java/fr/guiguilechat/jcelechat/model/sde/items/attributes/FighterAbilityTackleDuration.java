package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Duration
 */
public class FighterAbilityTackleDuration
    extends IntAttribute
{
    public final static FighterAbilityTackleDuration INSTANCE = new FighterAbilityTackleDuration();

    @Override
    public int getId() {
        return  2238;
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
        return "FighterAbilityTackleDuration";
    }
}
