package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * 
 */
public class HeatAttenuationMed
    extends DoubleAttribute
{
    public static final HeatAttenuationMed INSTANCE = new HeatAttenuationMed();

    @Override
    public int getId() {
        return  1261;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
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
        return "HeatAttenuationMed";
    }
}
