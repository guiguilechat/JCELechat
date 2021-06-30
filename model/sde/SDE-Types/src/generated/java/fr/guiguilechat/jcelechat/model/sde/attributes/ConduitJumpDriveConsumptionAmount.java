package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Number of units needed to conduit jump
 */
public class ConduitJumpDriveConsumptionAmount
    extends IntAttribute
{
    public static final ConduitJumpDriveConsumptionAmount INSTANCE = new ConduitJumpDriveConsumptionAmount();

    @Override
    public int getId() {
        return  3131;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
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
        return false;
    }

    @Override
    public String toString() {
        return "ConduitJumpDriveConsumptionAmount";
    }
}
