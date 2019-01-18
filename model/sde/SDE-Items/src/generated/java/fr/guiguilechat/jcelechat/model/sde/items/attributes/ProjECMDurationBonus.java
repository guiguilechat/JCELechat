package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Bonus to duration of modules requiring projected ECM.
 */
public class ProjECMDurationBonus
    extends IntAttribute
{
    public static final ProjECMDurationBonus INSTANCE = new ProjECMDurationBonus();

    @Override
    public int getId() {
        return  1193;
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
        return "ProjECMDurationBonus";
    }
}
