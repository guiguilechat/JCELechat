package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * reduction in MicroWarp Drive signature
 */
public class MWDSignatureRadiusBonus
    extends IntAttribute
{
    public static final MWDSignatureRadiusBonus INSTANCE = new MWDSignatureRadiusBonus();

    @Override
    public int getId() {
        return  1803;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
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
        return "MWDSignatureRadiusBonus";
    }
}
