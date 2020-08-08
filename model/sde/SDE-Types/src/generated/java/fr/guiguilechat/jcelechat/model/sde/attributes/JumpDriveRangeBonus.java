package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Jump range bonus for jump drive operation.
 */
public class JumpDriveRangeBonus
    extends IntAttribute
{
    public static final JumpDriveRangeBonus INSTANCE = new JumpDriveRangeBonus();

    @Override
    public int getId() {
        return  870;
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
        return "JumpDriveRangeBonus";
    }
}
