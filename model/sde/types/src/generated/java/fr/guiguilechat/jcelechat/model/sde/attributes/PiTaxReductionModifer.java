package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class PiTaxReductionModifer
    extends IntAttribute
{
    public static final PiTaxReductionModifer INSTANCE = new PiTaxReductionModifer();

    @Override
    public int getId() {
        return  1925;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "PiTaxReductionModifer";
    }
}
