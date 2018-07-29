package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * droneDamageBonus
 */
public class DroneDamageBonus
    extends DoubleAttribute
{
    public final static DroneDamageBonus INSTANCE = new DroneDamageBonus();

    @Override
    public int getId() {
        return  1255;
    }

    @Override
    public int getCatId() {
        return  10;
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
        return false;
    }

    @Override
    public String toString() {
        return "DroneDamageBonus";
    }
}
