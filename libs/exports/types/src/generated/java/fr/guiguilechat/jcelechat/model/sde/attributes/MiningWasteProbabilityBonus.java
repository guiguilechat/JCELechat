package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class MiningWasteProbabilityBonus
    extends IntAttribute
{
    public static final MiningWasteProbabilityBonus INSTANCE = new MiningWasteProbabilityBonus();

    @Override
    public int getId() {
        return  6053;
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
        return "MiningWasteProbabilityBonus";
    }
}
