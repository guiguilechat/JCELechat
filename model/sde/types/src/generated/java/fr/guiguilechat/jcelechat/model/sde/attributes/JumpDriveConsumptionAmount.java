package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Number of units it consumes per light year.
 */
public class JumpDriveConsumptionAmount
    extends IntAttribute
{
    public static final JumpDriveConsumptionAmount INSTANCE = new JumpDriveConsumptionAmount();

    @Override
    public int getId() {
        return  868;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public Number getDefaultValue() {
        return  2000.0;
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
        return "JumpDriveConsumptionAmount";
    }
}
