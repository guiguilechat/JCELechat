package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class TargetPainterResistanceBonus
    extends IntAttribute
{
    public static final TargetPainterResistanceBonus INSTANCE = new TargetPainterResistanceBonus();

    @Override
    public int getId() {
        return  2424;
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
        return "TargetPainterResistanceBonus";
    }
}
