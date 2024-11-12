package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class SpeedFactorFloor
    extends IntAttribute
{
    public static final SpeedFactorFloor INSTANCE = new SpeedFactorFloor();

    @Override
    public int getId() {
        return  2266;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return -99.0;
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
        return "SpeedFactorFloor";
    }
}
