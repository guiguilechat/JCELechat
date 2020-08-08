package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * A percentage chance to not change targets 0.0 - 1.0. 1.0 they will never change targets 0.0 they will always change targets
 */
public class AIChanceToNotTargetSwitch
    extends DoubleAttribute
{
    public static final AIChanceToNotTargetSwitch INSTANCE = new AIChanceToNotTargetSwitch();

    @Override
    public int getId() {
        return  1651;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "AIChanceToNotTargetSwitch";
    }
}
