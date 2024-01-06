package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Speed bonus when doing invention or reverse engineering
 */
public class InventionReverseEngineeringResearchSpeed
    extends IntAttribute
{
    public static final InventionReverseEngineeringResearchSpeed INSTANCE = new InventionReverseEngineeringResearchSpeed();

    @Override
    public int getId() {
        return  1959;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  1.0;
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
        return "InventionReverseEngineeringResearchSpeed";
    }
}
