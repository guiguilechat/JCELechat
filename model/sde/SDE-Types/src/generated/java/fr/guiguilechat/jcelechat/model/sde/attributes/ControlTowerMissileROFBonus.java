package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class ControlTowerMissileROFBonus
    extends IntAttribute
{
    public static final ControlTowerMissileROFBonus INSTANCE = new ControlTowerMissileROFBonus();

    @Override
    public int getId() {
        return  755;
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
        return "ControlTowerMissileROFBonus";
    }
}
