package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Signifies that this module if activated, will prevent ejection from the ship it is fitted to and extend the log out ship removal timer.
 */
public class DisallowEarlyDeactivation
    extends IntAttribute
{
    public static final DisallowEarlyDeactivation INSTANCE = new DisallowEarlyDeactivation();

    @Override
    public int getId() {
        return  906;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "DisallowEarlyDeactivation";
    }
}
