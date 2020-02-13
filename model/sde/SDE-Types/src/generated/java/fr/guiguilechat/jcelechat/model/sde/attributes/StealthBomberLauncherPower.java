package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Used for stealth bombers to decrease power need on cruise launchers.
 */
public class StealthBomberLauncherPower
    extends DoubleAttribute
{
    public static final StealthBomberLauncherPower INSTANCE = new StealthBomberLauncherPower();

    @Override
    public int getId() {
        return  837;
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
        return  1.0;
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
        return "StealthBomberLauncherPower";
    }
}
