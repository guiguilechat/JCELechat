package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The amount of the given resource type needed to be consumed for each activation cycle of this structure.
 */
public class ConsumptionQuantity
    extends IntAttribute
{
    public static final ConsumptionQuantity INSTANCE = new ConsumptionQuantity();

    @Override
    public int getId() {
        return  714;
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
        return true;
    }

    @Override
    public String toString() {
        return "ConsumptionQuantity";
    }
}
