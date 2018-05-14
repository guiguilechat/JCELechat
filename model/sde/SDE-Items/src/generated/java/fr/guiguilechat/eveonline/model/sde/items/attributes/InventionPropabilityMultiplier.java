package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Modifies base chance of successful invention
 */
public class InventionPropabilityMultiplier
    extends DoubleAttribute
{
    public final static InventionPropabilityMultiplier INSTANCE = new InventionPropabilityMultiplier();

    @Override
    public int getId() {
        return  1112;
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
}
