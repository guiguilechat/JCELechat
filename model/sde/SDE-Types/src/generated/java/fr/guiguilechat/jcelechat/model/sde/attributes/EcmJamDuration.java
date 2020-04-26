package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Length of jam duration
 */
public class EcmJamDuration
    extends IntAttribute
{
    public static final EcmJamDuration INSTANCE = new EcmJamDuration();

    @Override
    public int getId() {
        return  2822;
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
        return "EcmJamDuration";
    }
}
