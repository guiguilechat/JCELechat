package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * + / - modifier to a ship ladar strength
 */
public class ScanLadarStrengthModifier
    extends IntAttribute
{
    public static final ScanLadarStrengthModifier INSTANCE = new ScanLadarStrengthModifier();

    @Override
    public int getId() {
        return  1566;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
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
        return "ScanLadarStrengthModifier";
    }
}
