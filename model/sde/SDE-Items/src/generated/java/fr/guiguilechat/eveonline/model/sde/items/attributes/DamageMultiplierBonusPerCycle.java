package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * 
 */
public class DamageMultiplierBonusPerCycle
    extends DoubleAttribute
{
    public final static DamageMultiplierBonusPerCycle INSTANCE = new DamageMultiplierBonusPerCycle();

    @Override
    public int getId() {
        return  2733;
    }

    @Override
    public int getCatId() {
        return  29;
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
        return "DamageMultiplierBonusPerCycle";
    }
}
