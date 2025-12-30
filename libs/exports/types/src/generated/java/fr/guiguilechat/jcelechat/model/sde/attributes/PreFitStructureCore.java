package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Module type ID to pre-fit as structure core
 */
public class PreFitStructureCore
    extends IntAttribute
{
    public static final PreFitStructureCore INSTANCE = new PreFitStructureCore();

    @Override
    public int getId() {
        return  5701;
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
        return "PreFitStructureCore";
    }
}
