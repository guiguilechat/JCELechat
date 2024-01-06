package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Max research gang size bonus
 */
public class ResearchGangSizeBonus
    extends IntAttribute
{
    public static final ResearchGangSizeBonus INSTANCE = new ResearchGangSizeBonus();

    @Override
    public int getId() {
        return  407;
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
        return "ResearchGangSizeBonus";
    }
}
