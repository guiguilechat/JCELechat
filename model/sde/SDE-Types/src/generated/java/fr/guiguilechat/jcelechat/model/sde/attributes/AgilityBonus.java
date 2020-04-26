package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Bonus to the agility for a ship.
 */
public class AgilityBonus
    extends DoubleAttribute
{
    public static final AgilityBonus INSTANCE = new AgilityBonus();

    @Override
    public int getId() {
        return  151;
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
        return "AgilityBonus";
    }
}
