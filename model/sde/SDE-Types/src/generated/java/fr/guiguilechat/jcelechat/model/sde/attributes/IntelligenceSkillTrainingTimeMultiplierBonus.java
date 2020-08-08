package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Bonus or penalty to the percentage time it takes to train skills with Intelligence as the primary attribute.
 */
public class IntelligenceSkillTrainingTimeMultiplierBonus
    extends DoubleAttribute
{
    public static final IntelligenceSkillTrainingTimeMultiplierBonus INSTANCE = new IntelligenceSkillTrainingTimeMultiplierBonus();

    @Override
    public int getId() {
        return  229;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "IntelligenceSkillTrainingTimeMultiplierBonus";
    }
}
