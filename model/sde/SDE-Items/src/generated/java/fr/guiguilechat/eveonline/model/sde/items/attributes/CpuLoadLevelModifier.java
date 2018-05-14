package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Used to calculate cpu load multiplier for PI links
 */
public class CpuLoadLevelModifier
    extends DoubleAttribute
{
    public final static CpuLoadLevelModifier INSTANCE = new CpuLoadLevelModifier();

    @Override
    public int getId() {
        return  1635;
    }

    @Override
    public int getCatId() {
        return  1;
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
}
