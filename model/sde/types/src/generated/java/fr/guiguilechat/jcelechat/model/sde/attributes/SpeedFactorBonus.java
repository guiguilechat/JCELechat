package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Afterburner and Microwarpdrive Max Velocity Bonus
 */
public class SpeedFactorBonus
    extends IntAttribute
{
    public static final SpeedFactorBonus INSTANCE = new SpeedFactorBonus();

    @Override
    public int getId() {
        return  1164;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "SpeedFactorBonus";
    }
}
