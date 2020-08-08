package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * The maximum security level at which the structure can be anchored. Used as a non-functional display attribute on some deployables.
 */
public class AnchoringSecurityLevelMax
    extends DoubleAttribute
{
    public static final AnchoringSecurityLevelMax INSTANCE = new AnchoringSecurityLevelMax();

    @Override
    public int getId() {
        return  1032;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  1.0;
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
        return "AnchoringSecurityLevelMax";
    }
}
