package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * The chance that a passer by will be chosen as a target of a scan for contraband.
 */
public class ContrabandScanChance
    extends DoubleAttribute
{
    public static final ContrabandScanChance INSTANCE = new ContrabandScanChance();

    @Override
    public int getId() {
        return  725;
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
        return "ContrabandScanChance";
    }
}
