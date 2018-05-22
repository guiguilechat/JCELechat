package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Shield transfer amount multiplier
 */
public class ShieldBonusMultiplier
    extends DoubleAttribute
{
    public final static ShieldBonusMultiplier INSTANCE = new ShieldBonusMultiplier();

    @Override
    public int getId() {
        return  1496;
    }

    @Override
    public int getCatId() {
        return  2;
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
        return "ShieldBonusMultiplier";
    }
}
