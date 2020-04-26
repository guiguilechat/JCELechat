package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Skill attribute that reduces time for reactions
 *  jobs
 */
public class ReactionTimeBonus
    extends IntAttribute
{
    public static final ReactionTimeBonus INSTANCE = new ReactionTimeBonus();

    @Override
    public int getId() {
        return  2660;
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
        return "ReactionTimeBonus";
    }
}
