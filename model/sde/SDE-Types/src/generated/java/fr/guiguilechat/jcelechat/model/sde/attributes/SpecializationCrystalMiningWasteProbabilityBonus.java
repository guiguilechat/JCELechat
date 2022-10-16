package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Attribute added to the waste probability (mainly used in mining crystals)
 */
public class SpecializationCrystalMiningWasteProbabilityBonus
    extends RealAttribute
{
    public static final SpecializationCrystalMiningWasteProbabilityBonus INSTANCE = new SpecializationCrystalMiningWasteProbabilityBonus();

    @Override
    public int getId() {
        return  3160;
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
        return "SpecializationCrystalMiningWasteProbabilityBonus";
    }
}
