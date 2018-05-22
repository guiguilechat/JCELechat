package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * 
 */
public class TargetPainterStrengthMultiplier
    extends DoubleAttribute
{
    public final static TargetPainterStrengthMultiplier INSTANCE = new TargetPainterStrengthMultiplier();

    @Override
    public int getId() {
        return  1968;
    }

    @Override
    public int getCatId() {
        return  21;
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
        return "TargetPainterStrengthMultiplier";
    }
}
