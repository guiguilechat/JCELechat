package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Cost multiplier per m3 volume of this commodity when importing to a planet
 */
public class ImportTaxMultiplier
    extends IntAttribute
{
    public static final ImportTaxMultiplier INSTANCE = new ImportTaxMultiplier();

    @Override
    public int getId() {
        return  1640;
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
        return "ImportTaxMultiplier";
    }
}
