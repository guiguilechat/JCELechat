package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


public class StructureGasDecompressionEfficiencyBonus
    extends RealAttribute
{
    public static final StructureGasDecompressionEfficiencyBonus INSTANCE = new StructureGasDecompressionEfficiencyBonus();

    @Override
    public int getId() {
        return  3261;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
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
        return "StructureGasDecompressionEfficiencyBonus";
    }
}
