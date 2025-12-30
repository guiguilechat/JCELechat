package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The amount of Raw Material stolen from active Moon Harvester Arrays each cycle.
 */
public class SiphonRawMaterial
    extends IntAttribute
{
    public static final SiphonRawMaterial INSTANCE = new SiphonRawMaterial();

    @Override
    public int getId() {
        return  1928;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "SiphonRawMaterial";
    }
}
