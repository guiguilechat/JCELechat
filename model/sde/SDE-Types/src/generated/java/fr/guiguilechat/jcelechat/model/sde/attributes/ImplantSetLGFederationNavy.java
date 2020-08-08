package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * 
 */
public class ImplantSetLGFederationNavy
    extends DoubleAttribute
{
    public static final ImplantSetLGFederationNavy INSTANCE = new ImplantSetLGFederationNavy();

    @Override
    public int getId() {
        return  1570;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "ImplantSetLGFederationNavy";
    }
}
