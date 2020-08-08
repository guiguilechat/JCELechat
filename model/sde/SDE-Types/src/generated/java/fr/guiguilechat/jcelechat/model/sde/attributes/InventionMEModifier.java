package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Modifies the mineral efficiency of invented BPCs
 */
public class InventionMEModifier
    extends DoubleAttribute
{
    public static final InventionMEModifier INSTANCE = new InventionMEModifier();

    @Override
    public int getId() {
        return  1113;
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
        return "InventionMEModifier";
    }
}
