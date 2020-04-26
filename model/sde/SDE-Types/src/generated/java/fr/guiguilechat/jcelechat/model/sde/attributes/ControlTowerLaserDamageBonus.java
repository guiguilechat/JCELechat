package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class ControlTowerLaserDamageBonus
    extends IntAttribute
{
    public static final ControlTowerLaserDamageBonus INSTANCE = new ControlTowerLaserDamageBonus();

    @Override
    public int getId() {
        return  728;
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
        return "ControlTowerLaserDamageBonus";
    }
}
