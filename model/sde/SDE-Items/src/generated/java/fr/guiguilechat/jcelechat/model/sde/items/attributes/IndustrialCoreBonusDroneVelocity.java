package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * 
 */
public class IndustrialCoreBonusDroneVelocity
    extends IntAttribute
{
    public final static IndustrialCoreBonusDroneVelocity INSTANCE = new IndustrialCoreBonusDroneVelocity();

    @Override
    public int getId() {
        return  2584;
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
        return "IndustrialCoreBonusDroneVelocity";
    }
}
