package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Range in light years the ship can maximum jump to.
 */
public class JumpDriveRange
    extends DoubleAttribute
{
    public static final JumpDriveRange INSTANCE = new JumpDriveRange();

    @Override
    public int getId() {
        return  867;
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
        return "JumpDriveRange";
    }
}
