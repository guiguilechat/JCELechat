package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Bonus to capacity (shield at least).
 */
public class CapacityBonus
    extends DoubleAttribute
{
    public static final CapacityBonus INSTANCE = new CapacityBonus();

    @Override
    public int getId() {
        return  72;
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
        return "CapacityBonus";
    }
}
