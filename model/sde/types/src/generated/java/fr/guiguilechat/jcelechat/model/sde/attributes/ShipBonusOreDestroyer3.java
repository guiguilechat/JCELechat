package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class ShipBonusOreDestroyer3
    extends IntAttribute
{
    public static final ShipBonusOreDestroyer3 INSTANCE = new ShipBonusOreDestroyer3();

    @Override
    public int getId() {
        return  5822;
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
        return "ShipBonusOreDestroyer3";
    }
}
