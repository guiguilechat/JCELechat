package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Remaining number of unused turret slots on the ship.
 */
public class TurretSlotsLeft
    extends IntAttribute
{
    public static final TurretSlotsLeft INSTANCE = new TurretSlotsLeft();

    @Override
    public int getId() {
        return  102;
    }

    @Override
    public int getCatId() {
        return  1;
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
        return "TurretSlotsLeft";
    }
}
