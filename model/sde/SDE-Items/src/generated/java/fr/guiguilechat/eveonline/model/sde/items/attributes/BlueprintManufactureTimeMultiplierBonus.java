package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Bonus or penalty to the percentage time it takes to manufacture from a blueprint.
 */
public class BlueprintManufactureTimeMultiplierBonus
    extends DoubleAttribute
{
    public final static BlueprintManufactureTimeMultiplierBonus INSTANCE = new BlueprintManufactureTimeMultiplierBonus();

    @Override
    public int getId() {
        return  222;
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
        return "BlueprintManufactureTimeMultiplierBonus";
    }
}
