package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * DO NOT MESS WITH
 */
public class ArmorUniformity
    extends DoubleAttribute
{
    public static final ArmorUniformity INSTANCE = new ArmorUniformity();

    @Override
    public int getId() {
        return  524;
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
        return "ArmorUniformity";
    }
}
