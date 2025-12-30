package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class ShipBonusOreDestroyer1
    extends IntAttribute
{
    public static final ShipBonusOreDestroyer1 INSTANCE = new ShipBonusOreDestroyer1();

    @Override
    public int getId() {
        return  5820;
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
        return "ShipBonusOreDestroyer1";
    }
}
