package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Bonus to the agility for a ship.
 */
public class AgilityBonus
    extends RealAttribute
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
        return "AgilityBonus";
    }
}
