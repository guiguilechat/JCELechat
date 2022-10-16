package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Affects the signature radius of the target in missile impact calculations.
 */
public class MissileEntityAoeCloudSizeMultiplier
    extends RealAttribute
{
    public static final MissileEntityAoeCloudSizeMultiplier INSTANCE = new MissileEntityAoeCloudSizeMultiplier();

    @Override
    public int getId() {
        return  858;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
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
