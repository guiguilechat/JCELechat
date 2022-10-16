package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Whether this ship has fleet hangars.
 */
public class HasFleetHangars
    extends IntAttribute
{
    public static final HasFleetHangars INSTANCE = new HasFleetHangars();

    @Override
    public int getId() {
        return  911;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  0.0;
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
        return "HasFleetHangars";
    }
}
