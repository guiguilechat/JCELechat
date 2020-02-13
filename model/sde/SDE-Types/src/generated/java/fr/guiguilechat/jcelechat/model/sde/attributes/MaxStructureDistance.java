package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The distance that structures have to be from a control tower in order to work with it.
 */
public class MaxStructureDistance
    extends IntAttribute
{
    public static final MaxStructureDistance INSTANCE = new MaxStructureDistance();

    @Override
    public int getId() {
        return  650;
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
        return "MaxStructureDistance";
    }
}
