package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * +/- bonus to the perception of a character.
 */
public class PerceptionBonus
    extends IntAttribute
{
    public static final PerceptionBonus INSTANCE = new PerceptionBonus();

    @Override
    public int getId() {
        return  178;
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
        return "PerceptionBonus";
    }
}
