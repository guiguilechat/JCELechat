package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Bonus to the willpower of a character specified by the player in character creation.
 */
public class CustomWillpowerBonus
    extends IntAttribute
{
    public static final CustomWillpowerBonus INSTANCE = new CustomWillpowerBonus();

    @Override
    public int getId() {
        return  171;
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
        return "CustomWillpowerBonus";
    }
}
