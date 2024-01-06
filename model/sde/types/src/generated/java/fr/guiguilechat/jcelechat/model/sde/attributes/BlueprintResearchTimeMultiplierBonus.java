package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Bonus or penalty to the percentage time it takes to research a blueprint.
 */
public class BlueprintResearchTimeMultiplierBonus
    extends RealAttribute
{
    public static final BlueprintResearchTimeMultiplierBonus INSTANCE = new BlueprintResearchTimeMultiplierBonus();

    @Override
    public int getId() {
        return  220;
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
        return "BlueprintResearchTimeMultiplierBonus";
    }
}
