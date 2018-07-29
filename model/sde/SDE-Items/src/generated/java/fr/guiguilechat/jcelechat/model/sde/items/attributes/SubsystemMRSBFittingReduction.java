package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * 
 */
public class SubsystemMRSBFittingReduction
    extends IntAttribute
{
    public final static SubsystemMRSBFittingReduction INSTANCE = new SubsystemMRSBFittingReduction();

    @Override
    public int getId() {
        return  2670;
    }

    @Override
    public int getCatId() {
        return  1;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
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
        return "SubsystemMRSBFittingReduction";
    }
}
