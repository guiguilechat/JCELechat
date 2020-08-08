package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class SignatureRadiusBonusPercent
    extends IntAttribute
{
    public static final SignatureRadiusBonusPercent INSTANCE = new SignatureRadiusBonusPercent();

    @Override
    public int getId() {
        return  973;
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
        return false;
    }

    @Override
    public String toString() {
        return "SignatureRadiusBonusPercent";
    }
}
