package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * Autogenerated skill attribute, CapacitorCapacityBonus
 */
public class CapacitorCapacityBonus
    extends DoubleAttribute
{
    public final static CapacitorCapacityBonus INSTANCE = new CapacitorCapacityBonus();

    @Override
    public int getId() {
        return  1079;
    }

    @Override
    public int getCatId() {
        return  5;
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
        return "CapacitorCapacityBonus";
    }
}