package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The maximum amount of skill points that the character can have before the item is unusable
 */
public class MaxCharacterSkillPointLimit
    extends IntAttribute
{
    public static final MaxCharacterSkillPointLimit INSTANCE = new MaxCharacterSkillPointLimit();

    @Override
    public int getId() {
        return  2459;
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
        return "MaxCharacterSkillPointLimit";
    }
}
