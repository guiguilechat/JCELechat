package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Bonus to fighter Velocity (%)
 */
public class FighterBonusVelocityPercent
    extends DoubleAttribute
{
    public final static FighterBonusVelocityPercent INSTANCE = new FighterBonusVelocityPercent();

    @Override
    public int getId() {
        return  2336;
    }

    @Override
    public int getCatId() {
        return  38;
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
