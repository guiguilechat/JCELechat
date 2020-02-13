package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The time targeting systems take to recalibrate after cloaking.
 */
public class CloakingTargetingDelay
    extends IntAttribute
{
    public static final CloakingTargetingDelay INSTANCE = new CloakingTargetingDelay();

    @Override
    public int getId() {
        return  560;
    }

    @Override
    public int getCatId() {
        return  6;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
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
        return "CloakingTargetingDelay";
    }
}
