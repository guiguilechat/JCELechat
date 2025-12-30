package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class StructureFullPowerStateHitpointMultiplier
    extends IntAttribute
{
    public static final StructureFullPowerStateHitpointMultiplier INSTANCE = new StructureFullPowerStateHitpointMultiplier();

    @Override
    public int getId() {
        return  2743;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public Number getDefaultValue() {
        return  1.0;
    }

    @Override
    public boolean getPublished() {
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "StructureFullPowerStateHitpointMultiplier";
    }
}
