package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * 
 */
public class FighterAbilityWarpDisruptionPointStrengthInterim
    extends IntAttribute
{
    public final static FighterAbilityWarpDisruptionPointStrengthInterim INSTANCE = new FighterAbilityWarpDisruptionPointStrengthInterim();

    @Override
    public int getId() {
        return  2206;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }
}
