package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Determines whether the maxRange attribute is a fixed length or a maximum length of the effect
 */
public class DoomsdayRangeIsFixed
    extends IntAttribute
{
    public static final DoomsdayRangeIsFixed INSTANCE = new DoomsdayRangeIsFixed();

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

    @Override
    public String toString() {
        return "DoomsdayRangeIsFixed";
    }
}
