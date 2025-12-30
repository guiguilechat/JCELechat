package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Chance of the remote shield boosting effect being used
 */
public class NpcRemoteShieldBoostChance
    extends IntAttribute
{
    public static final NpcRemoteShieldBoostChance INSTANCE = new NpcRemoteShieldBoostChance();

    @Override
    public int getId() {
        return  1459;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  1.0;
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
        return "NpcRemoteShieldBoostChance";
    }
}
