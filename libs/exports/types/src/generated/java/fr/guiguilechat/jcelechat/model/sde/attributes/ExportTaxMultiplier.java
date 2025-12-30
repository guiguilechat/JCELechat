package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Export tax multiplier when exporting this commodity off a planet.
 */
public class ExportTaxMultiplier
    extends IntAttribute
{
    public static final ExportTaxMultiplier INSTANCE = new ExportTaxMultiplier();

    @Override
    public int getId() {
        return  1641;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public Number getDefaultValue() {
        return  1.0;
    }

    @Override
    public boolean getPublished() {
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "ExportTaxMultiplier";
    }
}
