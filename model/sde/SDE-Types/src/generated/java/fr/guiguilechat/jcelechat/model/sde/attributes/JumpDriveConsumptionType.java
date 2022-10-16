package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Type that is used for consumption from cargo hold when activating jump drive operation.
 */
public class JumpDriveConsumptionType
    extends IntAttribute
{
    public static final JumpDriveConsumptionType INSTANCE = new JumpDriveConsumptionType();

    @Override
    public int getId() {
        return  866;
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
        return "JumpDriveConsumptionType";
    }
}
