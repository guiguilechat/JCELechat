package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class SubsystemBonusAmarrPropulsion2
    extends IntAttribute
{
    public static final SubsystemBonusAmarrPropulsion2 INSTANCE = new SubsystemBonusAmarrPropulsion2();

    @Override
    public int getId() {
        return  1512;
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
        return "SubsystemBonusAmarrPropulsion2";
    }
}
