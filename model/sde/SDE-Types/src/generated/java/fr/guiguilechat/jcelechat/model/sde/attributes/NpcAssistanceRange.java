package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Maximum distance to a friendly NPC so that remote repairs may be performed on it.
 */
public class NpcAssistanceRange
    extends IntAttribute
{
    public static final NpcAssistanceRange INSTANCE = new NpcAssistanceRange();

    @Override
    public int getId() {
        return  1464;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return false;
    }

    @Override
    public String toString() {
        return "NpcAssistanceRange";
    }
}
