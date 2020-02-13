package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Maximum number of locked targets that the character or their ships electronics can handle at any given time.  Both have individual limits which apply separately.
 */
public class MaxLockedTargets
    extends IntAttribute
{
    public static final MaxLockedTargets INSTANCE = new MaxLockedTargets();

    @Override
    public int getId() {
        return  192;
    }

    @Override
    public int getCatId() {
        return  6;
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
        return "MaxLockedTargets";
    }
}
