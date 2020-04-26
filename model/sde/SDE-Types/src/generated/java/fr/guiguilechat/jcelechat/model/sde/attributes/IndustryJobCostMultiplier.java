package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Multiplies the job cost for this blueprint type by the specified value
 */
public class IndustryJobCostMultiplier
    extends IntAttribute
{
    public static final IndustryJobCostMultiplier INSTANCE = new IndustryJobCostMultiplier();

    @Override
    public int getId() {
        return  1954;
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
        return "IndustryJobCostMultiplier";
    }
}
