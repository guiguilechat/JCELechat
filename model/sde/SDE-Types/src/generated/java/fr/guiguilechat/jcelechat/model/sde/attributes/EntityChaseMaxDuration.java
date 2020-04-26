package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The maximum amount of time chase is ever engaged for.
 */
public class EntityChaseMaxDuration
    extends IntAttribute
{
    public static final EntityChaseMaxDuration INSTANCE = new EntityChaseMaxDuration();

    @Override
    public int getId() {
        return  582;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  5000.0;
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
        return "EntityChaseMaxDuration";
    }
}
