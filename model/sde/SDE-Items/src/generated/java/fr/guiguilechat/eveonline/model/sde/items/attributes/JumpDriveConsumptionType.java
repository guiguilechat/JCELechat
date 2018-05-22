package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Type that is used for consumption from cargo hold when activating jump drive operation.
 */
public class JumpDriveConsumptionType
    extends IntAttribute
{
    public final static JumpDriveConsumptionType INSTANCE = new JumpDriveConsumptionType();

    @Override
    public int getId() {
        return  866;
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
        return "JumpDriveConsumptionType";
    }
}
