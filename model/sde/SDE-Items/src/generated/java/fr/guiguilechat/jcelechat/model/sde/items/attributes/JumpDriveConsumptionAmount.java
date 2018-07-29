package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Number of units it consumes per light year.
 */
public class JumpDriveConsumptionAmount
    extends IntAttribute
{
    public final static JumpDriveConsumptionAmount INSTANCE = new JumpDriveConsumptionAmount();

    @Override
    public int getId() {
        return  868;
    }

    @Override
    public int getCatId() {
        return  7;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
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
        return false;
    }

    @Override
    public String toString() {
        return "JumpDriveConsumptionAmount";
    }
}
