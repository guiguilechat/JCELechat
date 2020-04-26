package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * This attribute is a multiplier to the number of skill points required to train. Skill points required to train a skill = 250 * skillTimeConstant * sqrt(32)^(skillLevel - 1)
 */
public class SkillTimeConstant
    extends DoubleAttribute
{
    public static final SkillTimeConstant INSTANCE = new SkillTimeConstant();

    @Override
    public int getId() {
        return  275;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "SkillTimeConstant";
    }
}
