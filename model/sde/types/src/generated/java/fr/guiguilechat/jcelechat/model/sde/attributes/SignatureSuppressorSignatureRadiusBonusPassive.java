package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * the passive bonus to signature radius from signature suppressors
 */
public class SignatureSuppressorSignatureRadiusBonusPassive
    extends RealAttribute
{
    public static final SignatureSuppressorSignatureRadiusBonusPassive INSTANCE = new SignatureSuppressorSignatureRadiusBonusPassive();

    @Override
    public int getId() {
        return  3113;
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
        return "SignatureSuppressorSignatureRadiusBonusPassive";
    }
}
