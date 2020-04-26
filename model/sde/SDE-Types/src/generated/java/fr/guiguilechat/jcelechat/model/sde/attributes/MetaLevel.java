package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Authoring has been moved to FSD
 * The ranking of the module within its tech level
 */
public class MetaLevel
    extends IntAttribute
{
    public static final MetaLevel INSTANCE = new MetaLevel();

    @Override
    public int getId() {
        return  633;
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
        return "MetaLevel";
    }
}
