package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Bonus to signature radius granted by signature suppressor activation
 */
public class SignatureSuppressorSignatureRadiusBonusActive
    extends RealAttribute
{
    public static final SignatureSuppressorSignatureRadiusBonusActive INSTANCE = new SignatureSuppressorSignatureRadiusBonusActive();

    @Override
    public int getId() {
        return  3114;
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
        return "SignatureSuppressorSignatureRadiusBonusActive";
    }
}
