package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Bonus to chance of opening a container (for skills).
 */
public class AccessDifficultyBonusAbsolutePercent
    extends IntAttribute
{
    public static final AccessDifficultyBonusAbsolutePercent INSTANCE = new AccessDifficultyBonusAbsolutePercent();

    @Override
    public int getId() {
        return  1772;
    }

    @Override
    public int getCatId() {
        return  7;
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
        return "AccessDifficultyBonusAbsolutePercent";
    }
}
