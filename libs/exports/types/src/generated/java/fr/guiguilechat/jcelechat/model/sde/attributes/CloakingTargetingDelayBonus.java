package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Skill bonus for cloaking targeting delay.
 */
public class CloakingTargetingDelayBonus
    extends IntAttribute
{
    public static final CloakingTargetingDelayBonus INSTANCE = new CloakingTargetingDelayBonus();

    @Override
    public int getId() {
        return  619;
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
        return "CloakingTargetingDelayBonus";
    }
}
