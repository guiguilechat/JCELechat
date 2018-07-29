package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * 
 */
public class DoomsdayEnergyNeutSignatureRadius
    extends IntAttribute
{
    public final static DoomsdayEnergyNeutSignatureRadius INSTANCE = new DoomsdayEnergyNeutSignatureRadius();

    @Override
    public int getId() {
        return  2261;
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
        return "DoomsdayEnergyNeutSignatureRadius";
    }
}
