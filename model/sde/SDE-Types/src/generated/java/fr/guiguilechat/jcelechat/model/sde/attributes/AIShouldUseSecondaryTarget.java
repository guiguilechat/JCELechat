package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Should use secondary effect on other targets?
 */
public class AIShouldUseSecondaryTarget
    extends IntAttribute
{
    public static final AIShouldUseSecondaryTarget INSTANCE = new AIShouldUseSecondaryTarget();

    @Override
    public int getId() {
        return  1649;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "AIShouldUseSecondaryTarget";
    }
}
