package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Autogenerated skill attribute, gallenteTechMutator
 */
public class GallenteTechMutator
    extends IntAttribute
{
    public static final GallenteTechMutator INSTANCE = new GallenteTechMutator();

    @Override
    public int getId() {
        return  372;
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
        return "GallenteTechMutator";
    }
}