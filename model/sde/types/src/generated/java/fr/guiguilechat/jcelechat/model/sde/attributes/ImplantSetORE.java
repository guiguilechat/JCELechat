package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Harvest set bonus attribute
 */
public class ImplantSetORE
    extends RealAttribute
{
    public static final ImplantSetORE INSTANCE = new ImplantSetORE();

    @Override
    public int getId() {
        return  1292;
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
        return "ImplantSetORE";
    }
}
