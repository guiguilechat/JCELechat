package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


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
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
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
