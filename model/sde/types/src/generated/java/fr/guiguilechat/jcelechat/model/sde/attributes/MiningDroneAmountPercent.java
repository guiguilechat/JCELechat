package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Mining drone speed percent
 */
public class MiningDroneAmountPercent
    extends IntAttribute
{
    public static final MiningDroneAmountPercent INSTANCE = new MiningDroneAmountPercent();

    @Override
    public int getId() {
        return  428;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
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
