package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * 
 */
public class DoomsdayEnergyNeutAmount
    extends IntAttribute
{
    public final static DoomsdayEnergyNeutAmount INSTANCE = new DoomsdayEnergyNeutAmount();

    @Override
    public int getId() {
        return  2260;
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
        return "DoomsdayEnergyNeutAmount";
    }
}
