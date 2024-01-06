package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Distribution ID for sov upgrades in Guristas space
 */
public class DistributionIDGurista03
    extends IntAttribute
{
    public static final DistributionIDGurista03 INSTANCE = new DistributionIDGurista03();

    @Override
    public int getId() {
        return  1717;
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
        return "DistributionIDGurista03";
    }
}
