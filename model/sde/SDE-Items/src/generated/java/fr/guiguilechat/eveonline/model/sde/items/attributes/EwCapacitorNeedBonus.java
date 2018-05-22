package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Bonus attribute for capacitor need of EW and propulsion jamming.
 */
public class EwCapacitorNeedBonus
    extends IntAttribute
{
    public final static EwCapacitorNeedBonus INSTANCE = new EwCapacitorNeedBonus();

    @Override
    public int getId() {
        return  1190;
    }

    @Override
    public int getCatId() {
        return  7;
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
        return "EwCapacitorNeedBonus";
    }
}
