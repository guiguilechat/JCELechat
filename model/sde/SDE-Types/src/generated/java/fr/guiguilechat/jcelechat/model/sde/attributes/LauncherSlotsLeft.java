package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The number of remaining unused launcher slots.
 */
public class LauncherSlotsLeft
    extends IntAttribute
{
    public static final LauncherSlotsLeft INSTANCE = new LauncherSlotsLeft();

    @Override
    public int getId() {
        return  101;
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
        return "LauncherSlotsLeft";
    }
}
