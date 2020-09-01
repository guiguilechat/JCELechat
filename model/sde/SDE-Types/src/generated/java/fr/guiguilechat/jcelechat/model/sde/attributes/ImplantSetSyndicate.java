package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Edge set bonus attribute
 */
public class ImplantSetSyndicate
    extends RealAttribute
{
    public static final ImplantSetSyndicate INSTANCE = new ImplantSetSyndicate();

    @Override
    public int getId() {
        return  1291;
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
        return "ImplantSetSyndicate";
    }
}
