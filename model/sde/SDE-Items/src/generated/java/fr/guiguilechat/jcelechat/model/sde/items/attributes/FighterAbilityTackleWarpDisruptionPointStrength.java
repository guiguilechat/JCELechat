package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Warp Disruption Strength
 */
public class FighterAbilityTackleWarpDisruptionPointStrength
    extends IntAttribute
{
    public final static FighterAbilityTackleWarpDisruptionPointStrength INSTANCE = new FighterAbilityTackleWarpDisruptionPointStrength();

    @Override
    public int getId() {
        return  2425;
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
        return true;
    }

    @Override
    public String toString() {
        return "FighterAbilityTackleWarpDisruptionPointStrength";
    }
}
