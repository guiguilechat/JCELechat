package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Bonus or penalty to the percentage time it takes to train skills with Memory as the primary attribute.
 */
public class MemorySkillTrainingTimeMultiplierBonus
    extends RealAttribute
{
    public static final MemorySkillTrainingTimeMultiplierBonus INSTANCE = new MemorySkillTrainingTimeMultiplierBonus();

    @Override
    public int getId() {
        return  230;
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
        return "MemorySkillTrainingTimeMultiplierBonus";
    }
}
