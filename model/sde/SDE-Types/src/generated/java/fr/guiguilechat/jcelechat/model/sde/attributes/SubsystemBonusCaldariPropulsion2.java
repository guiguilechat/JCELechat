package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * 
 */
public class SubsystemBonusCaldariPropulsion2
    extends RealAttribute
{
    public static final SubsystemBonusCaldariPropulsion2 INSTANCE = new SubsystemBonusCaldariPropulsion2();

    @Override
    public int getId() {
        return  1513;
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
        return "SubsystemBonusCaldariPropulsion2";
    }
}
