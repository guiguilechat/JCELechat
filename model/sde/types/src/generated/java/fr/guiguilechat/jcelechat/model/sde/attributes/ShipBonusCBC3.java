package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


public class ShipBonusCBC3
    extends RealAttribute
{
    public static final ShipBonusCBC3 INSTANCE = new ShipBonusCBC3();

    @Override
    public int getId() {
        return  5044;
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
        return true;
    }

    @Override
    public String toString() {
        return "ShipBonusCBC3";
    }
}
