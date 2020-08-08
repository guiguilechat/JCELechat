package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * 
 */
public class CloakingCpuNeedBonus
    extends DoubleAttribute
{
    public static final CloakingCpuNeedBonus INSTANCE = new CloakingCpuNeedBonus();

    @Override
    public int getId() {
        return  649;
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
        return "CloakingCpuNeedBonus";
    }
}
