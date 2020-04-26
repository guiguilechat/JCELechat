package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * if set to 1 the ship is immune to directional scan
 */
public class DscanImmune
    extends IntAttribute
{
    public static final DscanImmune INSTANCE = new DscanImmune();

    @Override
    public int getId() {
        return  1958;
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
        return "DscanImmune";
    }
}
