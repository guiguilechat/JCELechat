package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * 
 */
public class SubsystemBonusGallenteDefensive3
    extends DoubleAttribute
{
    public final static SubsystemBonusGallenteDefensive3 INSTANCE = new SubsystemBonusGallenteDefensive3();

    @Override
    public int getId() {
        return  2684;
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
        return "SubsystemBonusGallenteDefensive3";
    }
}
