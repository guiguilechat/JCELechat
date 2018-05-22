package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * The number of seconds that the reinforcement exit time will be adjusted by. exitTime +- attribute
 */
public class ReinforcementVariance
    extends IntAttribute
{
    public final static ReinforcementVariance INSTANCE = new ReinforcementVariance();

    @Override
    public int getId() {
        return  1613;
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
        return  10800.0;
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
        return "ReinforcementVariance";
    }
}
