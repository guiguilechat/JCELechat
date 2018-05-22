package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Signature Radius of the AOE Effect
 */
public class DoomsdayAOESignatureRadius
    extends IntAttribute
{
    public final static DoomsdayAOESignatureRadius INSTANCE = new DoomsdayAOESignatureRadius();

    @Override
    public int getId() {
        return  2281;
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
        return "DoomsdayAOESignatureRadius";
    }
}
