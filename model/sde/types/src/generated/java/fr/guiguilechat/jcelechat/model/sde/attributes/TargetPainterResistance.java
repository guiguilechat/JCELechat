package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Resistance against Target Painters
 */
public class TargetPainterResistance
    extends RealAttribute
{
    public static final TargetPainterResistance INSTANCE = new TargetPainterResistance();

    @Override
    public int getId() {
        return  2114;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public Number getDefaultValue() {
        return  1.0;
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
        return "TargetPainterResistance";
    }
}
