package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Duration
 */
public class FighterAbilityWarpDisruptionDuration
    extends IntAttribute
{
    public static final FighterAbilityWarpDisruptionDuration INSTANCE = new FighterAbilityWarpDisruptionDuration();

    @Override
    public int getId() {
        return  2203;
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
        return "FighterAbilityWarpDisruptionDuration";
    }
}
