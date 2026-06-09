package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class BoosterMinerMaxRangeBonus
    extends IntAttribute
{
    public static final BoosterMinerMaxRangeBonus INSTANCE = new BoosterMinerMaxRangeBonus();

    @Override
    public int getId() {
        return  6098;
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
        return "BoosterMinerMaxRangeBonus";
    }
}
