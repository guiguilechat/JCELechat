package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class SubsystemBonusMinmatarDefensive2
    extends IntAttribute
{
    public static final SubsystemBonusMinmatarDefensive2 INSTANCE = new SubsystemBonusMinmatarDefensive2();

    @Override
    public int getId() {
        return  1526;
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
        return "SubsystemBonusMinmatarDefensive2";
    }
}
