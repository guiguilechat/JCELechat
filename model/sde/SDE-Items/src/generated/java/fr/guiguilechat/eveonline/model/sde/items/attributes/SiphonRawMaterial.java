package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * The amount of Raw Material stolen from active Moon Harvester Arrays each cycle.
 */
public class SiphonRawMaterial
    extends IntAttribute
{
    public final static SiphonRawMaterial INSTANCE = new SiphonRawMaterial();

    @Override
    public int getId() {
        return  1928;
    }

    @Override
    public int getCatId() {
        return  7;
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
        return "SiphonRawMaterial";
    }
}
