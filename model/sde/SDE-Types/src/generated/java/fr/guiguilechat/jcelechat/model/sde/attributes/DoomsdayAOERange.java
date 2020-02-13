package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Radius of the AOE Effect
 */
public class DoomsdayAOERange
    extends IntAttribute
{
    public static final DoomsdayAOERange INSTANCE = new DoomsdayAOERange();

    @Override
    public int getId() {
        return  2279;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "DoomsdayAOERange";
    }
}
