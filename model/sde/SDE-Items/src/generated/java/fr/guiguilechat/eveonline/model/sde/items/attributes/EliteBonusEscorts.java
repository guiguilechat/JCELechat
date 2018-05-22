package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * bonus for escort class frigates
 */
public class EliteBonusEscorts
    extends IntAttribute
{
    public final static EliteBonusEscorts INSTANCE = new EliteBonusEscorts();

    @Override
    public int getId() {
        return  597;
    }

    @Override
    public int getCatId() {
        return  9;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  10.0;
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
        return "EliteBonusEscorts";
    }
}
