package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Bonus to HAW Missile Launcher Rate of Fire
 */
public class SiegeHAWMissileROFBonus
    extends IntAttribute
{
    public static final SiegeHAWMissileROFBonus INSTANCE = new SiegeHAWMissileROFBonus();

    @Override
    public int getId() {
        return  2821;
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
        return "SiegeHAWMissileROFBonus";
    }
}
