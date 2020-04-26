package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Duration of the AOE Effect
 */
public class DoomsdayAOEDuration
    extends IntAttribute
{
    public static final DoomsdayAOEDuration INSTANCE = new DoomsdayAOEDuration();

    @Override
    public int getId() {
        return  2280;
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
        return "DoomsdayAOEDuration";
    }
}
