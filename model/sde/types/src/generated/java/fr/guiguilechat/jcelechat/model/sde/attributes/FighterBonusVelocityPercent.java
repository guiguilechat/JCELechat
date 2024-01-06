package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Bonus to fighter Velocity (%)
 */
public class FighterBonusVelocityPercent
    extends RealAttribute
{
    public static final FighterBonusVelocityPercent INSTANCE = new FighterBonusVelocityPercent();

    @Override
    public int getId() {
        return  2336;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "FighterBonusVelocityPercent";
    }
}
