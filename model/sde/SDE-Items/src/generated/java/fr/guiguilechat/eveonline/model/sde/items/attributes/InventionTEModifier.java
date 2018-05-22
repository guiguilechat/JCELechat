package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Modifies the time efficiency of invented BPCs
 */
public class InventionTEModifier
    extends DoubleAttribute
{
    public final static InventionTEModifier INSTANCE = new InventionTEModifier();

    @Override
    public int getId() {
        return  1114;
    }

    @Override
    public int getCatId() {
        return  9;
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
