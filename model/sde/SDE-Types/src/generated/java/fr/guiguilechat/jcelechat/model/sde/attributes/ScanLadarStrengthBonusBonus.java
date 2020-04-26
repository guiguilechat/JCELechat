package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


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
