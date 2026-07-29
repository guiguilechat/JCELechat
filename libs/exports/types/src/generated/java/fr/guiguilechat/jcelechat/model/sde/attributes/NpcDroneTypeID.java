package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class NpcDroneTypeID
    extends IntAttribute
{
    public static final NpcDroneTypeID INSTANCE = new NpcDroneTypeID();

    @Override
    public int getId() {
        return  6271;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
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
        return false;
    }

    @Override
    public String toString() {
        return "NpcDroneTypeID";
    }
}
