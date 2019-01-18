package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Npc Bounty Multiplier
 */
public class BountyMultiplier
    extends IntAttribute
{
    public static final BountyMultiplier INSTANCE = new BountyMultiplier();

    @Override
    public int getId() {
        return  626;
    }

    @Override
    public int getCatId() {
        return  9;
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
        return "BountyMultiplier";
    }
}
