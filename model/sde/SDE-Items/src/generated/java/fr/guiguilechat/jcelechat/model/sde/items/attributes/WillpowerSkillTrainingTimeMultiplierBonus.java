package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * Bonus or penalty to the percentage time it takes to train skills with Willpower as the primary attribute.
 */
public class WillpowerSkillTrainingTimeMultiplierBonus
    extends DoubleAttribute
{
    public static final WillpowerSkillTrainingTimeMultiplierBonus INSTANCE = new WillpowerSkillTrainingTimeMultiplierBonus();

    @Override
    public int getId() {
        return  232;
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
        return "WillpowerSkillTrainingTimeMultiplierBonus";
    }
}
