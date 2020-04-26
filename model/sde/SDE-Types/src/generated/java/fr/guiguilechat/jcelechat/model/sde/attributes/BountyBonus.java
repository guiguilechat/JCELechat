package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Flat Bonus To NPC Bountys
 */
public class BountyBonus
    extends IntAttribute
{
    public static final BountyBonus INSTANCE = new BountyBonus();

    @Override
    public int getId() {
        return  625;
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
        return "BountyBonus";
    }
}
