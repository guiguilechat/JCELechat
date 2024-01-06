package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The range of the agent's communication sphere
 */
public class AgentCommRange
    extends IntAttribute
{
    public static final AgentCommRange INSTANCE = new AgentCommRange();

    @Override
    public int getId() {
        return  841;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  100000.0;
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
        return "AgentCommRange";
    }
}
