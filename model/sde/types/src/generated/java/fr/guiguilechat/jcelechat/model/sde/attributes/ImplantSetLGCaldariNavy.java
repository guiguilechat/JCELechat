package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * 
 */
public class ImplantSetLGCaldariNavy
    extends RealAttribute
{
    public static final ImplantSetLGCaldariNavy INSTANCE = new ImplantSetLGCaldariNavy();

    @Override
    public int getId() {
        return  1571;
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
        return "ImplantSetLGCaldariNavy";
    }
}
