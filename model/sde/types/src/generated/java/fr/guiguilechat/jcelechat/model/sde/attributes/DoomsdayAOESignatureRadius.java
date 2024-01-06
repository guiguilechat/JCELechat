package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Signature Radius of the AOE Effect
 */
public class DoomsdayAOESignatureRadius
    extends IntAttribute
{
    public static final DoomsdayAOESignatureRadius INSTANCE = new DoomsdayAOESignatureRadius();

    @Override
    public int getId() {
        return  2281;
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
        return "DoomsdayAOESignatureRadius";
    }
}
