package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * How damaged does a teammates shield need to be before it'll be repaired.
 *  0.1 means: Must be below 90% shields to get repairs
 *  0.9 means: Must be below 10% shields to get repairs
 */
public class NpcRemoteShieldBoostThreshold
    extends DoubleAttribute
{
    public static final NpcRemoteShieldBoostThreshold INSTANCE = new NpcRemoteShieldBoostThreshold();

    @Override
    public int getId() {
        return  1462;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  0.75;
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
        return "NpcRemoteShieldBoostThreshold";
    }
}
