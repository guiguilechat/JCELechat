package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Can be fitted to
 */
public class CanFitShipGroup06
    extends IntAttribute
{
    public static final CanFitShipGroup06 INSTANCE = new CanFitShipGroup06();

    @Override
    public int getId() {
        return  1879;
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
        return "CanFitShipGroup06";
    }
}
