package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Multiplied by Lancer Dreadnoughts skill level
 */
public class ShipBonusAdvancedDreadnought2
    extends IntAttribute
{
    public static final ShipBonusAdvancedDreadnought2 INSTANCE = new ShipBonusAdvancedDreadnought2();

    @Override
    public int getId() {
        return  5418;
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
        return "ShipBonusAdvancedDreadnought2";
    }
}
