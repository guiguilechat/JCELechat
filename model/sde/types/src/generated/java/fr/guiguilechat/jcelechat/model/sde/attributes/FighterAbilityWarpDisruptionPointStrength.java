package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Warp Disruption Strength
 */
public class FighterAbilityWarpDisruptionPointStrength
    extends IntAttribute
{
    public static final FighterAbilityWarpDisruptionPointStrength INSTANCE = new FighterAbilityWarpDisruptionPointStrength();

    @Override
    public int getId() {
        return  2205;
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
        return "FighterAbilityWarpDisruptionPointStrength";
    }
}
