package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class OverloadPainterStrengthBonus
    extends IntAttribute
{
    public static final OverloadPainterStrengthBonus INSTANCE = new OverloadPainterStrengthBonus();

    @Override
    public int getId() {
        return  1937;
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
        return "OverloadPainterStrengthBonus";
    }
}
