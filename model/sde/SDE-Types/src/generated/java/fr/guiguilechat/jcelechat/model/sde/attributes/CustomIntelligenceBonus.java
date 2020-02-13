package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Bonus to the intelligence of a character specified by the player in character creation.
 */
public class CustomIntelligenceBonus
    extends IntAttribute
{
    public static final CustomIntelligenceBonus INSTANCE = new CustomIntelligenceBonus();

    @Override
    public int getId() {
        return  174;
    }

    @Override
    public int getCatId() {
        return  9;
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
        return "CustomIntelligenceBonus";
    }
}
