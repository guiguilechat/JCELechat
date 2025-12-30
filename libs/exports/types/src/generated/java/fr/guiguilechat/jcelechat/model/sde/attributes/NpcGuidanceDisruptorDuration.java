package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * 
 */
public class NpcGuidanceDisruptorDuration
    extends RealAttribute
{
    public static final NpcGuidanceDisruptorDuration INSTANCE = new NpcGuidanceDisruptorDuration();

    @Override
    public int getId() {
        return  2511;
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
        return "NpcGuidanceDisruptorDuration";
    }
}
