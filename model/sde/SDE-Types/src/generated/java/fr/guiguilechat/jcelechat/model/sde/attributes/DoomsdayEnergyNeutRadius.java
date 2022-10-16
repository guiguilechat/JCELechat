package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class DoomsdayEnergyNeutRadius
    extends IntAttribute
{
    public static final DoomsdayEnergyNeutRadius INSTANCE = new DoomsdayEnergyNeutRadius();

    @Override
    public int getId() {
        return  2259;
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
        return true;
    }

    @Override
    public String toString() {
        return "DoomsdayEnergyNeutRadius";
    }
}
