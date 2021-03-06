package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Bonus or penalty to the percentage time it takes to train skills with Charisma as the primary attribute.
 */
public class CharismaSkillTrainingTimeMultiplierBonus
    extends RealAttribute
{
    public static final CharismaSkillTrainingTimeMultiplierBonus INSTANCE = new CharismaSkillTrainingTimeMultiplierBonus();

    @Override
    public int getId() {
        return  228;
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
        return "CharismaSkillTrainingTimeMultiplierBonus";
    }
}
