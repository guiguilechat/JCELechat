package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Base export tax (ISK per m3 of volume) on commodities exported from a planet via this pin.
 */
public class ExportTax
    extends IntAttribute
{
    public static final ExportTax INSTANCE = new ExportTax();

    @Override
    public int getId() {
        return  1639;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
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
        return "ExportTax";
    }
}
