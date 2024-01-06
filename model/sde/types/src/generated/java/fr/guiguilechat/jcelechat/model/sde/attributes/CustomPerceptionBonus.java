package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Bonus to the perception of a character specified by the player in character creation.
 */
public class CustomPerceptionBonus
    extends IntAttribute
{
    public static final CustomPerceptionBonus INSTANCE = new CustomPerceptionBonus();

    @Override
    public int getId() {
        return  172;
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
        return "CustomPerceptionBonus";
    }
}
