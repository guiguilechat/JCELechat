package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * +/- bonus to the willpower of a character.
 */
public class WillpowerBonus
    extends IntAttribute
{
    public static final WillpowerBonus INSTANCE = new WillpowerBonus();

    @Override
    public int getId() {
        return  179;
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
        return "WillpowerBonus";
    }
}
