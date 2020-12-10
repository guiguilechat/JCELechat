package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Bonus to chance of opening a container.
 */
public class AccessDifficultyBonus
    extends RealAttribute
{
    public static final AccessDifficultyBonus INSTANCE = new AccessDifficultyBonus();

    @Override
    public int getId() {
        return  902;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "AccessDifficultyBonus";
    }
}
