package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


public class GasDecompressionBaseEfficiency
    extends RealAttribute
{
    public static final GasDecompressionBaseEfficiency INSTANCE = new GasDecompressionBaseEfficiency();

    @Override
    public int getId() {
        return  3262;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  0.8;
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
        return "GasDecompressionBaseEfficiency";
    }
}
