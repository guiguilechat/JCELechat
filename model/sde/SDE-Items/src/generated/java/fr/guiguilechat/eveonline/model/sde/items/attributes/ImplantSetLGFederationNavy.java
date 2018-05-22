package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * 
 */
public class ImplantSetLGFederationNavy
    extends DoubleAttribute
{
    public final static ImplantSetLGFederationNavy INSTANCE = new ImplantSetLGFederationNavy();

    @Override
    public int getId() {
        return  1570;
    }

    @Override
    public int getCatId() {
        return  0;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
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
