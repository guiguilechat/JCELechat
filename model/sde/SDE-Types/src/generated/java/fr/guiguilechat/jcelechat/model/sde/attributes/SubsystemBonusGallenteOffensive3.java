package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * 
 */
public class SubsystemBonusGallenteOffensive3
    extends DoubleAttribute
{
    public static final SubsystemBonusGallenteOffensive3 INSTANCE = new SubsystemBonusGallenteOffensive3();

    @Override
    public int getId() {
        return  1532;
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
        return "SubsystemBonusGallenteOffensive3";
    }
}
