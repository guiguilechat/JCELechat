package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The probability of volume getting wasted every cycle
 */
public class MiningWasteProbability
    extends IntAttribute
{
    public static final MiningWasteProbability INSTANCE = new MiningWasteProbability();

    @Override
    public int getId() {
        return  3154;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
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
        return false;
    }

    @Override
    public String toString() {
        return "MiningWasteProbability";
    }
}
