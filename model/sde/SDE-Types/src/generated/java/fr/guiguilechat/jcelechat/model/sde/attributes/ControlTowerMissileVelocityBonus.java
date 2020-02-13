package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class ControlTowerMissileVelocityBonus
    extends IntAttribute
{
    public static final ControlTowerMissileVelocityBonus INSTANCE = new ControlTowerMissileVelocityBonus();

    @Override
    public int getId() {
        return  792;
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

    @Override
    public String toString() {
        return "ControlTowerMissileVelocityBonus";
    }
}
