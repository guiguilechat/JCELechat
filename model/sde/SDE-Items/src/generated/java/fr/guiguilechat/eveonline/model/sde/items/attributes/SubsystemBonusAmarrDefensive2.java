package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * 
 */
public class SubsystemBonusAmarrDefensive2
    extends IntAttribute
{
    public final static SubsystemBonusAmarrDefensive2 INSTANCE = new SubsystemBonusAmarrDefensive2();

    @Override
    public int getId() {
        return  1507;
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
        return "SubsystemBonusAmarrDefensive2";
    }
}
