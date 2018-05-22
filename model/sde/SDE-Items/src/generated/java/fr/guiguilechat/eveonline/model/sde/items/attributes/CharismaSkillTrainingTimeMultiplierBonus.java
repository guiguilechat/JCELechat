package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Bonus or penalty to the percentage time it takes to train skills with Charisma as the primary attribute.
 */
public class CharismaSkillTrainingTimeMultiplierBonus
    extends DoubleAttribute
{
    public final static CharismaSkillTrainingTimeMultiplierBonus INSTANCE = new CharismaSkillTrainingTimeMultiplierBonus();

    @Override
    public int getId() {
        return  228;
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
        return "CharismaSkillTrainingTimeMultiplierBonus";
    }
}
