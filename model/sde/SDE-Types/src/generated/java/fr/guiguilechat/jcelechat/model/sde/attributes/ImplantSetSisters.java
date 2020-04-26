package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Virtue set bonus attribute
 */
public class ImplantSetSisters
    extends DoubleAttribute
{
    public static final ImplantSetSisters INSTANCE = new ImplantSetSisters();

    @Override
    public int getId() {
        return  1284;
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
        return "ImplantSetSisters";
    }
}
