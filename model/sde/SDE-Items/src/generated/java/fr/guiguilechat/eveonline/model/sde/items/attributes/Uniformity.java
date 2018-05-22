package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
 */
public class Uniformity
    extends DoubleAttribute
{
    public final static Uniformity INSTANCE = new Uniformity();

    @Override
    public int getId() {
        return  136;
    }

    @Override
    public int getCatId() {
        return  2;
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
        return "Uniformity";
    }
}
