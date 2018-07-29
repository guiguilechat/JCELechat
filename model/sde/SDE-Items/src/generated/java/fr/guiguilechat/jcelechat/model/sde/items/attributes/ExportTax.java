package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Base export tax (ISK per m3 of volume) on commodities exported from a planet via this pin.
 */
public class ExportTax
    extends IntAttribute
{
    public final static ExportTax INSTANCE = new ExportTax();

    @Override
    public int getId() {
        return  1639;
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
        return "ExportTax";
    }
}
