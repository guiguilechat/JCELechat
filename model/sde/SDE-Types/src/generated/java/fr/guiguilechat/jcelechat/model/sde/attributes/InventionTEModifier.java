package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Modifies the time efficiency of invented BPCs
 */
public class InventionTEModifier
    extends RealAttribute
{
    public static final InventionTEModifier INSTANCE = new InventionTEModifier();

    @Override
    public int getId() {
        return  1114;
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
        return "InventionTEModifier";
    }
}
