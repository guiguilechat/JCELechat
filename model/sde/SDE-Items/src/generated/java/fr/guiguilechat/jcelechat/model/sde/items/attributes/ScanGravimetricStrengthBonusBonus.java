package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Bonus to Gravimetric Strength bonus
 */
public class ScanGravimetricStrengthBonusBonus
    extends IntAttribute
{
    public final static ScanGravimetricStrengthBonusBonus INSTANCE = new ScanGravimetricStrengthBonusBonus();

    @Override
    public int getId() {
        return  2072;
    }

    @Override
    public int getCatId() {
        return  7;
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
        return "ScanGravimetricStrengthBonusBonus";
    }
}
