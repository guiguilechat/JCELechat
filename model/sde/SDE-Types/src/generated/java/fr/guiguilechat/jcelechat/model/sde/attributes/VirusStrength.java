package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The strength attribute for a Virus
 */
public class VirusStrength
    extends IntAttribute
{
    public static final VirusStrength INSTANCE = new VirusStrength();

    @Override
    public int getId() {
        return  1910;
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
        return "VirusStrength";
    }
}
