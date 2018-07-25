package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * 
 */
public class IndustrialCoreBonusDroneIceHarvesting
    extends IntAttribute
{
    public final static IndustrialCoreBonusDroneIceHarvesting INSTANCE = new IndustrialCoreBonusDroneIceHarvesting();

    @Override
    public int getId() {
        return  2586;
    }

    @Override
    public int getCatId() {
        return  37;
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