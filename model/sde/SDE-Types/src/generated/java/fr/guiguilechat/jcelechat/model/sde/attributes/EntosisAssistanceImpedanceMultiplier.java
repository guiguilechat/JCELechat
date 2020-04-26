package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * 
 */
public class EntosisAssistanceImpedanceMultiplier
    extends DoubleAttribute
{
    public static final EntosisAssistanceImpedanceMultiplier INSTANCE = new EntosisAssistanceImpedanceMultiplier();

    @Override
    public int getId() {
        return  2754;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public double getDefaultValue() {
        return  1.0;
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
        return "EntosisAssistanceImpedanceMultiplier";
    }
}
