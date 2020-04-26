package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class AoeVelocityBonusBonus
    extends IntAttribute
{
    public static final AoeVelocityBonusBonus INSTANCE = new AoeVelocityBonusBonus();

    @Override
    public int getId() {
        return  2024;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  1399.0;
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
        return "AoeVelocityBonusBonus";
    }
}
