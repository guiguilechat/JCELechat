package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Bonus or penalty to the percentage time it takes to train skills with Intelligence as the primary attribute.
 */
public class IntelligenceSkillTrainingTimeMultiplierBonus
    extends DoubleAttribute
{
    public final static IntelligenceSkillTrainingTimeMultiplierBonus INSTANCE = new IntelligenceSkillTrainingTimeMultiplierBonus();

    @Override
    public int getId() {
        return  229;
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
        return "IntelligenceSkillTrainingTimeMultiplierBonus";
    }
}
