package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Precursor Destroyer Skill Attribute
 */
public class ShipBonusPD1
    extends IntAttribute
{
    public static final ShipBonusPD1 INSTANCE = new ShipBonusPD1();

    @Override
    public int getId() {
        return  2799;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "ShipBonusPD1";
    }
}
