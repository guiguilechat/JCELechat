package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Bonus or penalty to the percentage time it takes to train skills with Perception as the primary attribute.
 */
public class PerceptionSkillTrainingTimeMultiplierBonus
    extends DoubleAttribute
{
    public static final PerceptionSkillTrainingTimeMultiplierBonus INSTANCE = new PerceptionSkillTrainingTimeMultiplierBonus();

    @Override
    public int getId() {
        return  231;
    }

    @Override
    public int getCatId() {
        return  9;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "PerceptionSkillTrainingTimeMultiplierBonus";
    }
}
