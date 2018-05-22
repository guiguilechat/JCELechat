package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Repair amount multiplier for remote repairers
 */
public class ArmorDamageAmountMultiplierRemote
    extends DoubleAttribute
{
    public final static ArmorDamageAmountMultiplierRemote INSTANCE = new ArmorDamageAmountMultiplierRemote();

    @Override
    public int getId() {
        return  1498;
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

    @Override
    public String toString() {
        return "ArmorDamageAmountMultiplierRemote";
    }
}
