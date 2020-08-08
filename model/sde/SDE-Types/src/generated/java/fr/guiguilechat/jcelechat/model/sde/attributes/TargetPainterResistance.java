package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Resistance against Target Painters
 */
public class TargetPainterResistance
    extends DoubleAttribute
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
