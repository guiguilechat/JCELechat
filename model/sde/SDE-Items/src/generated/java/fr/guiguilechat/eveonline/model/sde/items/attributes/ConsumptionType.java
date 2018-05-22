package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * The type of resource needed to be consumed for each activation cycle of this structure.
 */
public class ConsumptionType
    extends IntAttribute
{
    public final static ConsumptionType INSTANCE = new ConsumptionType();

    @Override
    public int getId() {
        return  713;
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
        return "ConsumptionType";
    }
}
