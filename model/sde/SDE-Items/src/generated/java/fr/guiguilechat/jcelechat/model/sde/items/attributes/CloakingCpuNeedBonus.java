package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * 
 */
public class CloakingCpuNeedBonus
    extends DoubleAttribute
{
    public final static CloakingCpuNeedBonus INSTANCE = new CloakingCpuNeedBonus();

    @Override
    public int getId() {
        return  649;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "CloakingCpuNeedBonus";
    }
}
