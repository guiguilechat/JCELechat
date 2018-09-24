package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Mining drone speed percent
 */
public class MiningDroneAmountPercent
    extends IntAttribute
{
    public final static MiningDroneAmountPercent INSTANCE = new MiningDroneAmountPercent();

    @Override
    public int getId() {
        return  428;
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
        return "MiningDroneAmountPercent";
    }
}
