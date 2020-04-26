package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class IndustrialCoreBonusDroneIceHarvesting
    extends IntAttribute
{
    public static final IndustrialCoreBonusDroneIceHarvesting INSTANCE = new IndustrialCoreBonusDroneIceHarvesting();

    @Override
    public int getId() {
        return  2586;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "IndustrialCoreBonusDroneIceHarvesting";
    }
}
