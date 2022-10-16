package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class MaxScanDeviationModifierModule
    extends IntAttribute
{
    public static final MaxScanDeviationModifierModule INSTANCE = new MaxScanDeviationModifierModule();

    @Override
    public int getId() {
        return  1905;
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
        return false;
    }

    @Override
    public String toString() {
        return "MaxScanDeviationModifierModule";
    }
}
