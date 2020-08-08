package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class ControlTowerLaserOptimalBonus
    extends IntAttribute
{
    public static final ControlTowerLaserOptimalBonus INSTANCE = new ControlTowerLaserOptimalBonus();

    @Override
    public int getId() {
        return  750;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "ControlTowerLaserOptimalBonus";
    }
}
