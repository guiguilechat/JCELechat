package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * How many starbase structures a character control.
 */
public class PosStructureControlAmount
    extends IntAttribute
{
    public static final PosStructureControlAmount INSTANCE = new PosStructureControlAmount();

    @Override
    public int getId() {
        return  1174;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "PosStructureControlAmount";
    }
}
