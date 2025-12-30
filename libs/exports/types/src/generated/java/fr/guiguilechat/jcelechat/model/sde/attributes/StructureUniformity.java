package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * DO NOT MESS WITH
 */
public class StructureUniformity
    extends RealAttribute
{
    public static final StructureUniformity INSTANCE = new StructureUniformity();

    @Override
    public int getId() {
        return  525;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  1.0;
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
        return "StructureUniformity";
    }
}
