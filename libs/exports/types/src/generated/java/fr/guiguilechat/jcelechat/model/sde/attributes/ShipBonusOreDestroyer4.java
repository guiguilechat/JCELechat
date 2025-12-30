package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class ShipBonusOreDestroyer4
    extends IntAttribute
{
    public static final ShipBonusOreDestroyer4 INSTANCE = new ShipBonusOreDestroyer4();

    @Override
    public int getId() {
        return  5953;
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
        return "ShipBonusOreDestroyer4";
    }
}
