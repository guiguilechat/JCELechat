package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class SubsystemBonusMinmatarCore3
    extends IntAttribute
{
    public static final SubsystemBonusMinmatarCore3 INSTANCE = new SubsystemBonusMinmatarCore3();

    @Override
    public int getId() {
        return  2687;
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
        return "SubsystemBonusMinmatarCore3";
    }
}
