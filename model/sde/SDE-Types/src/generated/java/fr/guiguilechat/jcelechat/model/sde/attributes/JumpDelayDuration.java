package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Delay in seconds; until you can jump again.
 */
public class JumpDelayDuration
    extends IntAttribute
{
    public static final JumpDelayDuration INSTANCE = new JumpDelayDuration();

    @Override
    public int getId() {
        return  1221;
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
        return "JumpDelayDuration";
    }
}
