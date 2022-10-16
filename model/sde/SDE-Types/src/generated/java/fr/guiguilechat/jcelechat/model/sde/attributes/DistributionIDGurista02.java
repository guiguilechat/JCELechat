package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Distribution ID for sov upgrades in Guristas space
 */
public class DistributionIDGurista02
    extends IntAttribute
{
    public static final DistributionIDGurista02 INSTANCE = new DistributionIDGurista02();

    @Override
    public int getId() {
        return  1716;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "DistributionIDGurista02";
    }
}
