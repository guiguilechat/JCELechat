package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Warp Disruption Strength
 */
public class FighterAbilityTackleWarpDisruptionPointStrength
    extends IntAttribute
{
    public static final FighterAbilityTackleWarpDisruptionPointStrength INSTANCE = new FighterAbilityTackleWarpDisruptionPointStrength();

    @Override
    public int getId() {
        return  2425;
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
        return true;
    }

    @Override
    public String toString() {
        return "FighterAbilityTackleWarpDisruptionPointStrength";
    }
}