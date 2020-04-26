package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The amount of skill points contained in this item
 */
public class ContainedSkillPoints
    extends IntAttribute
{
    public static final ContainedSkillPoints INSTANCE = new ContainedSkillPoints();

    @Override
    public int getId() {
        return  2461;
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
        return "ContainedSkillPoints";
    }
}
