package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class BehaviorEnergyNosferatuFalloff
    extends IntAttribute
{
    public static final BehaviorEnergyNosferatuFalloff INSTANCE = new BehaviorEnergyNosferatuFalloff();

    @Override
    public int getId() {
        return  2631;
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
        return "BehaviorEnergyNosferatuFalloff";
    }
}
