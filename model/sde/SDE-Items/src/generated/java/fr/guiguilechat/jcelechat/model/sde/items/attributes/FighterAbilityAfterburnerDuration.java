package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Duration
 */
public class FighterAbilityAfterburnerDuration
    extends IntAttribute
{
    public final static FighterAbilityAfterburnerDuration INSTANCE = new FighterAbilityAfterburnerDuration();

    @Override
    public int getId() {
        return  2158;
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
        return "FighterAbilityAfterburnerDuration";
    }
}
