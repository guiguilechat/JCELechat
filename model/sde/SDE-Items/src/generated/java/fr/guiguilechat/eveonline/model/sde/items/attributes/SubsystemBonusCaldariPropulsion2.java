package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * 
 */
public class SubsystemBonusCaldariPropulsion2
    extends DoubleAttribute
{
    public final static SubsystemBonusCaldariPropulsion2 INSTANCE = new SubsystemBonusCaldariPropulsion2();

    @Override
    public int getId() {
        return  1513;
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
}
