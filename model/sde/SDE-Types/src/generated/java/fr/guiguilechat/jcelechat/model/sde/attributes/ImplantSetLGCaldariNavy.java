package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * 
 */
public class ImplantSetLGCaldariNavy
    extends DoubleAttribute
{
    public static final ImplantSetLGCaldariNavy INSTANCE = new ImplantSetLGCaldariNavy();

    @Override
    public int getId() {
        return  1571;
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
        return "ImplantSetLGCaldariNavy";
    }
}
