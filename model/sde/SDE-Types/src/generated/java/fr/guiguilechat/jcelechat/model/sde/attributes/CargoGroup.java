package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The cargo group that can be loaded into this container
 */
public class CargoGroup
    extends IntAttribute
{
    public static final CargoGroup INSTANCE = new CargoGroup();

    @Override
    public int getId() {
        return  629;
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
        return "CargoGroup";
    }
}
