package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class ShipBonusOreDestroyer2
    extends IntAttribute
{
    public static final ShipBonusOreDestroyer2 INSTANCE = new ShipBonusOreDestroyer2();

    @Override
    public int getId() {
        return  5821;
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
        return "ShipBonusOreDestroyer2";
    }
}
