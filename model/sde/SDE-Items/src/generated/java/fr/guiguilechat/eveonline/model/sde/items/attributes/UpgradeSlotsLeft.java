package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * How many upgrades can by fitted to this ship.
 */
public class UpgradeSlotsLeft
    extends IntAttribute
{
    public final static UpgradeSlotsLeft INSTANCE = new UpgradeSlotsLeft();

    @Override
    public int getId() {
        return  1154;
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
}
