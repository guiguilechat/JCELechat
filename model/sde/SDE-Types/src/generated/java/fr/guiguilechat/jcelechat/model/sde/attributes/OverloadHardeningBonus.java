package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class OverloadHardeningBonus
    extends IntAttribute
{
    public static final OverloadHardeningBonus INSTANCE = new OverloadHardeningBonus();

    @Override
    public int getId() {
        return  1208;
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
        return "OverloadHardeningBonus";
    }
}
