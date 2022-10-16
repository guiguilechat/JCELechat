package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * CPU cost of extractor head
 */
public class EcuExtractorHeadCPU
    extends IntAttribute
{
    public static final EcuExtractorHeadCPU INSTANCE = new EcuExtractorHeadCPU();

    @Override
    public int getId() {
        return  1690;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
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
