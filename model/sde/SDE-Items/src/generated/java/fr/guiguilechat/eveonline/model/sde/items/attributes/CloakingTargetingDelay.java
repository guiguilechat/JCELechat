package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * The time targeting systems take to recalibrate after cloaking.
 */
public class CloakingTargetingDelay
    extends IntAttribute
{
    public final static CloakingTargetingDelay INSTANCE = new CloakingTargetingDelay();

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
