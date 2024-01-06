package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class SubsystemBonusGallenteDefensive2
    extends IntAttribute
{
    public static final SubsystemBonusGallenteDefensive2 INSTANCE = new SubsystemBonusGallenteDefensive2();

    @Override
    public int getId() {
        return  1517;
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
        return "SubsystemBonusGallenteDefensive2";
    }
}
