package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * The % of Frigate assembly cost a player has to pay to assemble a frigate
 */
public class FrigateConstructionCost
    extends IntAttribute
{
    public final static FrigateConstructionCost INSTANCE = new FrigateConstructionCost();

    @Override
    public int getId() {
        return  388;
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
        return  100.0;
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
        return "FrigateConstructionCost";
    }
}
