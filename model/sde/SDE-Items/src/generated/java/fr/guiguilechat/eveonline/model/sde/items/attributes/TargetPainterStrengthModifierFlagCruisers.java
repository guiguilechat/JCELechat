package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * 
 */
public class TargetPainterStrengthModifierFlagCruisers
    extends IntAttribute
{
    public final static TargetPainterStrengthModifierFlagCruisers INSTANCE = new TargetPainterStrengthModifierFlagCruisers();

    @Override
    public int getId() {
        return  2755;
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
        return "TargetPainterStrengthModifierFlagCruisers";
    }
}