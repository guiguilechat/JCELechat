package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * agentID to use when initiating NPC communications with this type.
 */
public class AgentID
    extends IntAttribute
{
    public static final AgentID INSTANCE = new AgentID();

    @Override
    public int getId() {
        return  840;
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
        return "AgentID";
    }
}
