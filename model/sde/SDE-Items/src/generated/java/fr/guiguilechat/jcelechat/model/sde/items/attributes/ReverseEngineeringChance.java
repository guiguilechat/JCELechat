package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Chance of making a research breakthrough when researching a higher tech level of a blueprint
 */
public class ReverseEngineeringChance
    extends IntAttribute
{
    public static final ReverseEngineeringChance INSTANCE = new ReverseEngineeringChance();

    @Override
    public int getId() {
        return  397;
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
        return "ReverseEngineeringChance";
    }
}
