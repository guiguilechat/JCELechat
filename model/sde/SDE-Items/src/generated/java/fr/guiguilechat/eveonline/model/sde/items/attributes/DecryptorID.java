package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Used to show usable decryptors when starting reverse engineering based on data interface
 */
public class DecryptorID
    extends IntAttribute
{
    public final static DecryptorID INSTANCE = new DecryptorID();

    @Override
    public int getId() {
        return  1115;
    }

    @Override
    public int getCatId() {
        return  9;
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
        return "DecryptorID";
    }
}
