package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Centurion set bonus attribute
 */
public class ImplantSetMordus
    extends RealAttribute
{
    public static final ImplantSetMordus INSTANCE = new ImplantSetMordus();

    @Override
    public int getId() {
        return  1293;
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
        return "ImplantSetMordus";
    }
}
