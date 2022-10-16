package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Extra batteries to add capacitor.
 */
public class CapacitorBonus
    extends IntAttribute
{
    public static final CapacitorBonus INSTANCE = new CapacitorBonus();

    @Override
    public int getId() {
        return  67;
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
        return "CapacitorBonus";
    }
}
