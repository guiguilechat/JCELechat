package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Modifies the max runs in a blueprint created through invention
 */
public class InventionMaxRunModifier
    extends DoubleAttribute
{
    public static final InventionMaxRunModifier INSTANCE = new InventionMaxRunModifier();

    @Override
    public int getId() {
        return  1124;
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
        return "InventionMaxRunModifier";
    }
}
