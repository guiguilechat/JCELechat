package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * 
 */
public class SubsystemBonusAmarrDefensive3
    extends DoubleAttribute
{
    public static final SubsystemBonusAmarrDefensive3 INSTANCE = new SubsystemBonusAmarrDefensive3();

    @Override
    public int getId() {
        return  2680;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
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
        return "SubsystemBonusAmarrDefensive3";
    }
}
