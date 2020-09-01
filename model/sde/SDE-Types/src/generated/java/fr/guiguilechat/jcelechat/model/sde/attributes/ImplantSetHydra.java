package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


public class ImplantSetHydra
    extends RealAttribute
{
    public static final ImplantSetHydra INSTANCE = new ImplantSetHydra();

    @Override
    public int getId() {
        return  3027;
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
        return "ImplantSetHydra";
    }
}
