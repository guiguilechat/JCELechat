package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Additional amount of locked targets that can be handled.
 */
public class MaxLockedTargetsBonus
    extends IntAttribute
{
    public final static MaxLockedTargetsBonus INSTANCE = new MaxLockedTargetsBonus();

    @Override
    public int getId() {
        return  235;
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
        return "MaxLockedTargetsBonus";
    }
}
