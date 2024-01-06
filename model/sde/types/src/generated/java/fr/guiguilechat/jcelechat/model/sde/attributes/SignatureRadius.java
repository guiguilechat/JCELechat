package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Signature Radius is used for turret tracking and scanning.
 */
public class SignatureRadius
    extends RealAttribute
{
    public static final SignatureRadius INSTANCE = new SignatureRadius();

    @Override
    public int getId() {
        return  552;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public Number getDefaultValue() {
        return  100.0;
    }

    @Override
    public boolean getPublished() {
        return true;
    }

    @Override
    public boolean getStackable() {
        return false;
    }

    @Override
    public String toString() {
        return "SignatureRadius";
    }
}
