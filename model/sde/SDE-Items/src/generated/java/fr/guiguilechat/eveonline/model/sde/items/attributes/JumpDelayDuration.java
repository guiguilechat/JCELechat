package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Delay in seconds; until you can jump again.
 */
public class JumpDelayDuration
    extends IntAttribute
{
    public final static JumpDelayDuration INSTANCE = new JumpDelayDuration();

    @Override
    public int getId() {
        return  1221;
    }

    @Override
    public int getCatId() {
        return  7;
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
        return "JumpDelayDuration";
    }
}
