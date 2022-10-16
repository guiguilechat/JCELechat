package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * 
 */
public class ImplantSetFederationNavy
    extends RealAttribute
{
    public static final ImplantSetFederationNavy INSTANCE = new ImplantSetFederationNavy();

    @Override
    public int getId() {
        return  1553;
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
        return "ImplantSetFederationNavy";
    }
}
