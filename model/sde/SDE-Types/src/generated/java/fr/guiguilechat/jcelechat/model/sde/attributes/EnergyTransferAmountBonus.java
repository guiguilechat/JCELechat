package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * 
 */
public class EnergyTransferAmountBonus
    extends RealAttribute
{
    public static final EnergyTransferAmountBonus INSTANCE = new EnergyTransferAmountBonus();

    @Override
    public int getId() {
        return  1840;
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
        return "EnergyTransferAmountBonus";
    }
}
