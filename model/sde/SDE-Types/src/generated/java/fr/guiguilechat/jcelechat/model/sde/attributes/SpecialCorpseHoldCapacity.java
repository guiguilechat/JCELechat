package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * special corpse hold capacity
 */
public class SpecialCorpseHoldCapacity
    extends IntAttribute
{
    public static final SpecialCorpseHoldCapacity INSTANCE = new SpecialCorpseHoldCapacity();

    @Override
    public int getId() {
        return  2467;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return false;
    }

    @Override
    public String toString() {
        return "SpecialCorpseHoldCapacity";
    }
}
