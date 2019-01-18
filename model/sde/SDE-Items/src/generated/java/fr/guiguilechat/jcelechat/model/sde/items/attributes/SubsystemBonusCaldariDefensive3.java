package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * 
 */
public class SubsystemBonusCaldariDefensive3
    extends DoubleAttribute
{
    public static final SubsystemBonusCaldariDefensive3 INSTANCE = new SubsystemBonusCaldariDefensive3();

    @Override
    public int getId() {
        return  2682;
    }

    @Override
    public int getCatId() {
        return  7;
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
        return "SubsystemBonusCaldariDefensive3";
    }
}
