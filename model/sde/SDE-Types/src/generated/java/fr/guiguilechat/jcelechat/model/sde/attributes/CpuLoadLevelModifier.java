package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Used to calculate cpu load multiplier for PI links
 */
public class CpuLoadLevelModifier
    extends RealAttribute
{
    public static final CpuLoadLevelModifier INSTANCE = new CpuLoadLevelModifier();

    @Override
    public int getId() {
        return  1635;
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
        return true;
    }

    @Override
    public String toString() {
        return "CpuLoadLevelModifier";
    }
}
