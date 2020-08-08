package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * 
 */
public class SubsystemBonusGallentePropulsion2
    extends DoubleAttribute
{
    public static final SubsystemBonusGallentePropulsion2 INSTANCE = new SubsystemBonusGallentePropulsion2();

    @Override
    public int getId() {
        return  1520;
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
        return "SubsystemBonusGallentePropulsion2";
    }
}
