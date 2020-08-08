package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


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
        return "WillpowerSkillTrainingTimeMultiplierBonus";
    }
}
