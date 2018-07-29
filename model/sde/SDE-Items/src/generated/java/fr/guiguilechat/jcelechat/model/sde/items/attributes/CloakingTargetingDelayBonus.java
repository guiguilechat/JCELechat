package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Skill bonus for cloaking targeting delay.
 */
public class CloakingTargetingDelayBonus
    extends IntAttribute
{
    public final static CloakingTargetingDelayBonus INSTANCE = new CloakingTargetingDelayBonus();

    @Override
    public int getId() {
        return  619;
    }

    @Override
    public int getCatId() {
        return  6;
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
        return "CloakingTargetingDelayBonus";
    }
}
