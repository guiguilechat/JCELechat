package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class TargetPainterRangeModifierFlagCruisers
    extends IntAttribute
{
    public static final TargetPainterRangeModifierFlagCruisers INSTANCE = new TargetPainterRangeModifierFlagCruisers();

    @Override
    public int getId() {
        return  2756;
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
        return "TargetPainterRangeModifierFlagCruisers";
    }
}
