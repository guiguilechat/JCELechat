package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * XL Launcher ROF Bonus Percentage
 */
public class SiegeLauncherROFBonus
    extends IntAttribute
{
    public final static SiegeLauncherROFBonus INSTANCE = new SiegeLauncherROFBonus();

    @Override
    public int getId() {
        return  2305;
    }

    @Override
    public int getCatId() {
        return  37;
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
        return "SiegeLauncherROFBonus";
    }
}
