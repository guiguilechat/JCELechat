package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * CPU Penalty Reduction
 */
public class MiningUpgradeCPUReductionBonus
    extends IntAttribute
{
    public final static MiningUpgradeCPUReductionBonus INSTANCE = new MiningUpgradeCPUReductionBonus();

    @Override
    public int getId() {
        return  927;
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
}
