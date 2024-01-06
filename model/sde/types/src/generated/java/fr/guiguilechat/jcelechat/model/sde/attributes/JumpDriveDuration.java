package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The amount of time before the ship actually jumps.
 */
public class JumpDriveDuration
    extends IntAttribute
{
    public static final JumpDriveDuration INSTANCE = new JumpDriveDuration();

    @Override
    public int getId() {
        return  869;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  300000.0;
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
        return "JumpDriveDuration";
    }
}
