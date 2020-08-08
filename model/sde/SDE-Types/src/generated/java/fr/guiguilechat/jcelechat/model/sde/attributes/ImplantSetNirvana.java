package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Implant Set Nirvana
 */
public class ImplantSetNirvana
    extends DoubleAttribute
{
    public static final ImplantSetNirvana INSTANCE = new ImplantSetNirvana();

    @Override
    public int getId() {
        return  3017;
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
        return "ImplantSetNirvana";
    }
}
