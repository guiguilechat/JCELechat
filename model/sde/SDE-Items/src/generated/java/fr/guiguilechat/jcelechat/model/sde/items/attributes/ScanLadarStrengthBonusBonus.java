package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Bonus to Lader Strength bonus
 */
public class ScanLadarStrengthBonusBonus
    extends IntAttribute
{
    public static final ScanLadarStrengthBonusBonus INSTANCE = new ScanLadarStrengthBonusBonus();

    @Override
    public int getId() {
        return  2073;
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
        return "ScanLadarStrengthBonusBonus";
    }
}
