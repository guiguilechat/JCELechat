package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class BoosterTurretOptimalRangePenalty
    extends IntAttribute
{
    public static final BoosterTurretOptimalRangePenalty INSTANCE = new BoosterTurretOptimalRangePenalty();

    @Override
    public int getId() {
        return  1144;
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
        return "BoosterTurretOptimalRangePenalty";
    }
}
