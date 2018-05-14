package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * 
 */
public class ImplantSetLGImperialNavy
    extends DoubleAttribute
{
    public final static ImplantSetLGImperialNavy INSTANCE = new ImplantSetLGImperialNavy();

    @Override
    public int getId() {
        return  1569;
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
}
