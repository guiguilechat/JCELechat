package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Multiplier for the missile's flight time.
 */
public class MissileEntityFlightTimeMultiplier
    extends DoubleAttribute
{
    public static final MissileEntityFlightTimeMultiplier INSTANCE = new MissileEntityFlightTimeMultiplier();

    @Override
    public int getId() {
        return  646;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  1.0;
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
        return "MissileEntityFlightTimeMultiplier";
    }
}
