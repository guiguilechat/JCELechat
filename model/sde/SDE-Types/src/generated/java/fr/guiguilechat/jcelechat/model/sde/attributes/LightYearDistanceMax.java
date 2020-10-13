package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


public class LightYearDistanceMax
    extends RealAttribute
{
    public static final LightYearDistanceMax INSTANCE = new LightYearDistanceMax();

    @Override
    public int getId() {
        return  3097;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public Number getDefaultValue() {
        return  1.0;
    }

    @Override
    public boolean getPublished() {
        return false;
    }

    @Override
    public boolean getStackable() {
        return false;
    }

    @Override
    public String toString() {
        return "LightYearDistanceMax";
    }
}
