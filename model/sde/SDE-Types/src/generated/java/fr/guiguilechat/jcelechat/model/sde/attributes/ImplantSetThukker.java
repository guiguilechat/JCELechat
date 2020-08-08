package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Nomad set bonus attribute
 */
public class ImplantSetThukker
    extends DoubleAttribute
{
    public static final ImplantSetThukker INSTANCE = new ImplantSetThukker();

    @Override
    public int getId() {
        return  1282;
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
        return "ImplantSetThukker";
    }
}
