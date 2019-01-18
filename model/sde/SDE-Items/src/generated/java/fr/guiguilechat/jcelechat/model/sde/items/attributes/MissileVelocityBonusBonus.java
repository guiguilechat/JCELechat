package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * 
 */
public class MissileVelocityBonusBonus
    extends IntAttribute
{
    public static final MissileVelocityBonusBonus INSTANCE = new MissileVelocityBonusBonus();

    @Override
    public int getId() {
        return  2025;
    }

    @Override
    public int getCatId() {
        return  7;
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
        return "MissileVelocityBonusBonus";
    }
}
