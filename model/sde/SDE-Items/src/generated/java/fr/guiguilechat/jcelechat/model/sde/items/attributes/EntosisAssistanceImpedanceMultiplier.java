package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


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
    public int getCatId() {
        return  36;
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
