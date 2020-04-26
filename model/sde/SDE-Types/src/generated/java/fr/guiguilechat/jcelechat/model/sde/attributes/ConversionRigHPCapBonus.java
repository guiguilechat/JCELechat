package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class ConversionRigHPCapBonus
    extends IntAttribute
{
    public static final ConversionRigHPCapBonus INSTANCE = new ConversionRigHPCapBonus();

    @Override
    public int getId() {
        return  2772;
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
        return "ConversionRigHPCapBonus";
    }
}
