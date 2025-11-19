package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class ShipBonusSoEECS2
    extends IntAttribute
{
    public static final ShipBonusSoEECS2 INSTANCE = new ShipBonusSoEECS2();

    @Override
    public int getId() {
        return  5940;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  5.0;
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
        return "ShipBonusSoEECS2";
    }
}
