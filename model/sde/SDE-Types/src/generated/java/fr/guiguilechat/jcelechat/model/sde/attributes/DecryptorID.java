package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Used to show usable decryptors when starting reverse engineering based on data interface
 */
public class DecryptorID
    extends IntAttribute
{
    public static final DecryptorID INSTANCE = new DecryptorID();

    @Override
    public int getId() {
        return  1115;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "DecryptorID";
    }
}
