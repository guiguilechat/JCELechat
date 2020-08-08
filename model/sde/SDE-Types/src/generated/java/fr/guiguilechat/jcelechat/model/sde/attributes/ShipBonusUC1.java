package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


public class ShipBonusUC1
    extends DoubleAttribute
{
    public static final ShipBonusUC1 INSTANCE = new ShipBonusUC1();

    @Override
    public int getId() {
        return  3043;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "ShipBonusUC1";
    }
}
