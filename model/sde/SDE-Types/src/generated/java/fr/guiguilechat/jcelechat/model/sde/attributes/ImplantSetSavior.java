package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


public class ImplantSetSavior
    extends DoubleAttribute
{
    public static final ImplantSetSavior INSTANCE = new ImplantSetSavior();

    @Override
    public int getId() {
        return  3023;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "ImplantSetSavior";
    }
}
