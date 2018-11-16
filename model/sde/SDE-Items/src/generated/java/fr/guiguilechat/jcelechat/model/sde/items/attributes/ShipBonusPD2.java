package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Precursor Destroyer Skill Attribute
 */
public class ShipBonusPD2
    extends IntAttribute
{
    public final static ShipBonusPD2 INSTANCE = new ShipBonusPD2();

    @Override
    public int getId() {
        return  2800;
    }

    @Override
    public int getCatId() {
        return  9;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
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
        return "ShipBonusPD2";
    }
}
