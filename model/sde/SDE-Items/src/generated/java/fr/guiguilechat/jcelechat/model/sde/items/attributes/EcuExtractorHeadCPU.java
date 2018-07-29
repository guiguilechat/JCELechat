package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * CPU cost of extractor head
 */
public class EcuExtractorHeadCPU
    extends IntAttribute
{
    public final static EcuExtractorHeadCPU INSTANCE = new EcuExtractorHeadCPU();

    @Override
    public int getId() {
        return  1690;
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
        return  110.0;
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
        return "EcuExtractorHeadCPU";
    }
}
