package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * This controls the time that must pass between one target switch and another!
 */
public class AITargetSwitchTimer
    extends IntAttribute
{
    public static final AITargetSwitchTimer INSTANCE = new AITargetSwitchTimer();

    @Override
    public int getId() {
        return  1416;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  60000.0;
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
        return "AITargetSwitchTimer";
    }
}
