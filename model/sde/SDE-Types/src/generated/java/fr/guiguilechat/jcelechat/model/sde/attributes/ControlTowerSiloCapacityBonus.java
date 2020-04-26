package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class ControlTowerSiloCapacityBonus
    extends IntAttribute
{
    public static final ControlTowerSiloCapacityBonus INSTANCE = new ControlTowerSiloCapacityBonus();

    @Override
    public int getId() {
        return  757;
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
        return "ControlTowerSiloCapacityBonus";
    }
}
