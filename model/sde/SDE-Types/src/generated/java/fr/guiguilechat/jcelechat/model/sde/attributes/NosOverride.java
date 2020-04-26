package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * NOS override allows a nosferatu module to drain the target capacitor below the current ships capacitor level.
 */
public class NosOverride
    extends IntAttribute
{
    public static final NosOverride INSTANCE = new NosOverride();

    @Override
    public int getId() {
        return  1945;
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
        return "NosOverride";
    }
}
