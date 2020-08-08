package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Base importation tax (ISK per m3 of volume) for commodities imported to pin.
 */
public class ImportTax
    extends DoubleAttribute
{
    public static final ImportTax INSTANCE = new ImportTax();

    @Override
    public int getId() {
        return  1638;
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
        return "ImportTax";
    }
}
