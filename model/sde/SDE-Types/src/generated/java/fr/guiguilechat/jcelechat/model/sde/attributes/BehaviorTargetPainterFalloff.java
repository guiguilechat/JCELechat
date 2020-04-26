package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class BehaviorTargetPainterFalloff
    extends IntAttribute
{
    public static final BehaviorTargetPainterFalloff INSTANCE = new BehaviorTargetPainterFalloff();

    @Override
    public int getId() {
        return  2525;
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
        return "BehaviorTargetPainterFalloff";
    }
}
