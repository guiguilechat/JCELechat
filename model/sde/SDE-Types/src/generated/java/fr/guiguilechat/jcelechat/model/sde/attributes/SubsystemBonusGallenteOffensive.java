package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * 
 */
public class SubsystemBonusGallenteOffensive
    extends DoubleAttribute
{
    public static final SubsystemBonusGallenteOffensive INSTANCE = new SubsystemBonusGallenteOffensive();

    @Override
    public int getId() {
        return  1439;
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
        return "SubsystemBonusGallenteOffensive";
    }
}
