package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * The capacitor charge required to disengage this crystal from the unit it is installed in.
 */
public class UnfitCapCost
    extends IntAttribute
{
    public final static UnfitCapCost INSTANCE = new UnfitCapCost();

    @Override
    public int getId() {
        return  785;
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
        return "UnfitCapCost";
    }
}
