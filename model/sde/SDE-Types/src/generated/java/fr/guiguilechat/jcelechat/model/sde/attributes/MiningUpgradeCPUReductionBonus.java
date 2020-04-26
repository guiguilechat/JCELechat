package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * CPU Penalty Reduction
 */
public class MiningUpgradeCPUReductionBonus
    extends IntAttribute
{
    public static final MiningUpgradeCPUReductionBonus INSTANCE = new MiningUpgradeCPUReductionBonus();

    @Override
    public int getId() {
        return  927;
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
        return "MiningUpgradeCPUReductionBonus";
    }
}
