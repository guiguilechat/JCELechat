package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * XL Launcher ROF Bonus Percentage
 */
public class SiegeLauncherROFBonus
    extends IntAttribute
{
    public static final SiegeLauncherROFBonus INSTANCE = new SiegeLauncherROFBonus();

    @Override
    public int getId() {
        return  2305;
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
        return "SiegeLauncherROFBonus";
    }
}
