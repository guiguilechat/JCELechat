package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * The minimum security level at which the structure can be anchored.
 */
public class AnchoringSecurityLevelMin
    extends DoubleAttribute
{
    public final static AnchoringSecurityLevelMin INSTANCE = new AnchoringSecurityLevelMin();

    @Override
    public int getId() {
        return  1946;
    }

    @Override
    public int getCatId() {
        return  7;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return -1.0;
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
        return "AnchoringSecurityLevelMin";
    }
}
