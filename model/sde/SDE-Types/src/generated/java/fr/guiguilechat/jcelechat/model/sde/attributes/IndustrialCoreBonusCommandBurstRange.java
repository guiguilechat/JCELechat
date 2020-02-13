package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class IndustrialCoreBonusCommandBurstRange
    extends IntAttribute
{
    public static final IndustrialCoreBonusCommandBurstRange INSTANCE = new IndustrialCoreBonusCommandBurstRange();

    @Override
    public int getId() {
        return  2588;
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
        return "IndustrialCoreBonusCommandBurstRange";
    }
}
