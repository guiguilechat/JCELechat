package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class SubsystemBonusAmarrCore3
    extends IntAttribute
{
    public static final SubsystemBonusAmarrCore3 INSTANCE = new SubsystemBonusAmarrCore3();

    @Override
    public int getId() {
        return  2681;
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
        return "SubsystemBonusAmarrCore3";
    }
}
