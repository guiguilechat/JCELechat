package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


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
    public int getCatId() {
        return  37;
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
