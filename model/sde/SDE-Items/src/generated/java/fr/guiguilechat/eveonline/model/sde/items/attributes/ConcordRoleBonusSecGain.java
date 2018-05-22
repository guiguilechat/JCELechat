package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * 
 */
public class ConcordRoleBonusSecGain
    extends IntAttribute
{
    public final static ConcordRoleBonusSecGain INSTANCE = new ConcordRoleBonusSecGain();

    @Override
    public int getId() {
        return  2620;
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
        return "ConcordRoleBonusSecGain";
    }
}
