package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Determines whether the maxRange attribute is a fixed length or a maximum length of the effect
 */
public class DoomsdayRangeIsFixed
    extends IntAttribute
{
    public final static DoomsdayRangeIsFixed INSTANCE = new DoomsdayRangeIsFixed();

    @Override
    public int getId() {
        return  2430;
    }

    @Override
    public int getCatId() {
        return  39;
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
