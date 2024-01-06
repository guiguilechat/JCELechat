package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Multiplied by Lancer Dreadnought skill level
 */
public class ShipBonusAdvancedDreadnought1
    extends IntAttribute
{
    public static final ShipBonusAdvancedDreadnought1 INSTANCE = new ShipBonusAdvancedDreadnought1();

    @Override
    public int getId() {
        return  5417;
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
        return "ShipBonusAdvancedDreadnought1";
    }
}
