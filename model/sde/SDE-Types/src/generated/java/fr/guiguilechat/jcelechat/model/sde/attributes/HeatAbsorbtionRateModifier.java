package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * 
 */
public class HeatAbsorbtionRateModifier
    extends DoubleAttribute
{
    public static final HeatAbsorbtionRateModifier INSTANCE = new HeatAbsorbtionRateModifier();

    @Override
    public int getId() {
        return  1180;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  0.0;
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
        return "HeatAbsorbtionRateModifier";
    }
}
