package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Authoring has been moved to FSD
 * The ranking of the module within its tech level
 */
public class MetaLevelOld
    extends IntAttribute
{
    public static final MetaLevelOld INSTANCE = new MetaLevelOld();

    @Override
    public int getId() {
        return  633;
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
        return "MetaLevelOld";
    }
}
