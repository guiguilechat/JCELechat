package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class NpcGuidanceDisruptorFalloff
    extends IntAttribute
{
    public static final NpcGuidanceDisruptorFalloff INSTANCE = new NpcGuidanceDisruptorFalloff();

    @Override
    public int getId() {
        return  2513;
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
        return "NpcGuidanceDisruptorFalloff";
    }
}
