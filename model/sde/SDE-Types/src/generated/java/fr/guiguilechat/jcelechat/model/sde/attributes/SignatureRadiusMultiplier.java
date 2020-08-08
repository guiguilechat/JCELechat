package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * 
 */
public class SignatureRadiusMultiplier
    extends DoubleAttribute
{
    public static final SignatureRadiusMultiplier INSTANCE = new SignatureRadiusMultiplier();

    @Override
    public int getId() {
        return  652;
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
        return "SignatureRadiusMultiplier";
    }
}
