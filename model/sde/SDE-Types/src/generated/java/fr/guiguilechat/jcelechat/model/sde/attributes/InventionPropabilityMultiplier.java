package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Modifies base chance of successful invention
 */
public class InventionPropabilityMultiplier
    extends DoubleAttribute
{
    public static final InventionPropabilityMultiplier INSTANCE = new InventionPropabilityMultiplier();

    @Override
    public int getId() {
        return  1112;
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
        return "InventionPropabilityMultiplier";
    }
}
