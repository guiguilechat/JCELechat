package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * Affects the signature radius of the target in missile impact calculations.
 */
public class MissileEntityAoeCloudSizeMultiplier
    extends DoubleAttribute
{
    public final static MissileEntityAoeCloudSizeMultiplier INSTANCE = new MissileEntityAoeCloudSizeMultiplier();

    @Override
    public int getId() {
        return  858;
    }

    @Override
    public int getCatId() {
        return  30;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  1.0;
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
        return "MissileEntityAoeCloudSizeMultiplier";
    }
}
