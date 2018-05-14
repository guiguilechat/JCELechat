package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Armor repair amount multiplier
 */
public class ArmorDamageAmountMultiplier
    extends DoubleAttribute
{
    public final static ArmorDamageAmountMultiplier INSTANCE = new ArmorDamageAmountMultiplier();

    @Override
    public int getId() {
        return  1495;
    }

    @Override
    public int getCatId() {
        return  3;
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
}
