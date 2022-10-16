package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * 
 */
public class ImplantSetImperialNavy
    extends RealAttribute
{
    public static final ImplantSetImperialNavy INSTANCE = new ImplantSetImperialNavy();

    @Override
    public int getId() {
        return  1550;
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
        return "ImplantSetImperialNavy";
    }
}
