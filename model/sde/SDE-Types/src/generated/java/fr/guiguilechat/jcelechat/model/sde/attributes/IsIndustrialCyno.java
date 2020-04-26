package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class IsIndustrialCyno
    extends IntAttribute
{
    public static final IsIndustrialCyno INSTANCE = new IsIndustrialCyno();

    @Override
    public int getId() {
        return  2826;
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
        return "IsIndustrialCyno";
    }
}
