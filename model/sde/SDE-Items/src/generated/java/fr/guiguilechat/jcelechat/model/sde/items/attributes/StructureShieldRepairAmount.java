package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Amount of shield healed by structure repair effects
 */
public class StructureShieldRepairAmount
    extends IntAttribute
{
    public final static StructureShieldRepairAmount INSTANCE = new StructureShieldRepairAmount();

    @Override
    public int getId() {
        return  1171;
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
        return "StructureShieldRepairAmount";
    }
}
