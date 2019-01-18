package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Power cost for a extractor head
 */
public class EcuExtractorHeadPower
    extends IntAttribute
{
    public static final EcuExtractorHeadPower INSTANCE = new EcuExtractorHeadPower();

    @Override
    public int getId() {
        return  1691;
    }

    @Override
    public int getCatId() {
        return  0;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  500.0;
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
        return "EcuExtractorHeadPower";
    }
}
