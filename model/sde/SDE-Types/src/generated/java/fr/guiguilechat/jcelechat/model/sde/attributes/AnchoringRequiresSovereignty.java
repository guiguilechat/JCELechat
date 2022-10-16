package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Whether the structure requires the anchorers alliance to hold sovereignty in the system for it to be anchorable.  Only enforced if the security level is 0.4 or less.
 */
public class AnchoringRequiresSovereignty
    extends IntAttribute
{
    public static final AnchoringRequiresSovereignty INSTANCE = new AnchoringRequiresSovereignty();

    @Override
    public int getId() {
        return  1033;
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
        return "AnchoringRequiresSovereignty";
    }
}
