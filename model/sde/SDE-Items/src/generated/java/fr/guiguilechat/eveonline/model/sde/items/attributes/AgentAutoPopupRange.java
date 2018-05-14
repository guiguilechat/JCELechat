package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * If the player comes within this range of this agent-in-space, an automatic communication window popup will occur.
 */
public class AgentAutoPopupRange
    extends IntAttribute
{
    public final static AgentAutoPopupRange INSTANCE = new AgentAutoPopupRange();

    @Override
    public int getId() {
        return  844;
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
        return  5000.0;
    }

    @Override
    public boolean getPublished() {
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }
}
