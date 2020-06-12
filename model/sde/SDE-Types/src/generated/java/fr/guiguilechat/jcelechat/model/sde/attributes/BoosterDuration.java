package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Duration of booster, after this duration the booster is destroyed
 */
public class BoosterDuration
    extends DoubleAttribute
{
    public static final BoosterDuration INSTANCE = new BoosterDuration();

    @Override
    public int getId() {
        return  330;
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
        return "BoosterDuration";
    }
}
