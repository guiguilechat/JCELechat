package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Modifies miningAmountBonus
 */
public class MiningAmountBonusBonus
    extends IntAttribute
{
    public final static MiningAmountBonusBonus INSTANCE = new MiningAmountBonusBonus();

    @Override
    public int getId() {
        return  1938;
    }

    @Override
    public int getCatId() {
        return  0;
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
        return "MiningAmountBonusBonus";
    }
}
