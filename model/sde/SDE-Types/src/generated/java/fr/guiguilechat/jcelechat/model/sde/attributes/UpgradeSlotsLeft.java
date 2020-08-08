package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * How many rigs can by fitted to this ship.
 */
public class UpgradeSlotsLeft
    extends IntAttribute
{
    public static final UpgradeSlotsLeft INSTANCE = new UpgradeSlotsLeft();

    @Override
    public int getId() {
        return  1154;
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
        return "UpgradeSlotsLeft";
    }
}
