package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Authoring has been moved to FSD
 * Tech level of an item
 */
public class TechLevel
    extends IntAttribute
{
    public static final TechLevel INSTANCE = new TechLevel();

    @Override
    public int getId() {
        return  422;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "TechLevel";
    }
}
