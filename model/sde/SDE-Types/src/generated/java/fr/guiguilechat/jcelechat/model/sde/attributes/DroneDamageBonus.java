package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * droneDamageBonus
 */
public class DroneDamageBonus
    extends DoubleAttribute
{
    public static final DroneDamageBonus INSTANCE = new DroneDamageBonus();

    @Override
    public int getId() {
        return  1255;
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
        return false;
    }

    @Override
    public String toString() {
        return "DroneDamageBonus";
    }
}
