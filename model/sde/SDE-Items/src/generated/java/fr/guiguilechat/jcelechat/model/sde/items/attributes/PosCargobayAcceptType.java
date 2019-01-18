package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * cargo typeID allowed in structures
 */
public class PosCargobayAcceptType
    extends IntAttribute
{
    public static final PosCargobayAcceptType INSTANCE = new PosCargobayAcceptType();

    @Override
    public int getId() {
        return  1351;
    }

    @Override
    public int getCatId() {
        return  4;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  0.0;
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
        return "PosCargobayAcceptType";
    }
}
