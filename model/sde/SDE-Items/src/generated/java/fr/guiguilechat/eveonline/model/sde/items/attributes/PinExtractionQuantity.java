package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Base amount (in units) of commodities extracted by an extractor pin.
 */
public class PinExtractionQuantity
    extends IntAttribute
{
    public final static PinExtractionQuantity INSTANCE = new PinExtractionQuantity();

    @Override
    public int getId() {
        return  1642;
    }

    @Override
    public int getCatId() {
        return  7;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  100.0;
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
        return "PinExtractionQuantity";
    }
}
