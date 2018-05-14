package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * NOS override allows a nosferatu module to drain the target capacitor below the current ships capacitor level.
 */
public class NosOverride
    extends IntAttribute
{
    public final static NosOverride INSTANCE = new NosOverride();

    @Override
    public int getId() {
        return  1945;
    }

    @Override
    public int getCatId() {
        return  5;
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
}
