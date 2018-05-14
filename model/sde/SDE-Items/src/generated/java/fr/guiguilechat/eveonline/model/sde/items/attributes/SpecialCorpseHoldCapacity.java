package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * special corpse hold capacity
 */
public class SpecialCorpseHoldCapacity
    extends IntAttribute
{
    public final static SpecialCorpseHoldCapacity INSTANCE = new SpecialCorpseHoldCapacity();

    @Override
    public int getId() {
        return  2467;
    }

    @Override
    public int getCatId() {
        return  40;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return false;
    }
}
