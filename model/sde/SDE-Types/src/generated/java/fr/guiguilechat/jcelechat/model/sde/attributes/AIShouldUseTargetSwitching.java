package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * This controls how L1 AI target switches
 * When disabled AI_ChanceToNotTargetSwitch, AI_ShouldUseEffectMultiplier, and AI_ShouldUseSignatureRadius are disabled also.
 */
public class AIShouldUseTargetSwitching
    extends IntAttribute
{
    public static final AIShouldUseTargetSwitching INSTANCE = new AIShouldUseTargetSwitching();

    @Override
    public int getId() {
        return  1648;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "AIShouldUseTargetSwitching";
    }
}
