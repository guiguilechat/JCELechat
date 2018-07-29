package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * 
 */
public class DoomsdayEnergyNeutResistanceID
    extends IntAttribute
{
    public final static DoomsdayEnergyNeutResistanceID INSTANCE = new DoomsdayEnergyNeutResistanceID();

    @Override
    public int getId() {
        return  2609;
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
        return "DoomsdayEnergyNeutResistanceID";
    }
}
