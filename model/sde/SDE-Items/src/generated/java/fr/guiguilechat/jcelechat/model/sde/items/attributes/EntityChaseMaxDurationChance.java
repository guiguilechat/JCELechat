package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * The chance of engaging chase for the maximum duration.
 */
public class EntityChaseMaxDurationChance
    extends IntAttribute
{
    public static final EntityChaseMaxDurationChance INSTANCE = new EntityChaseMaxDurationChance();

    @Override
    public int getId() {
        return  583;
    }

    @Override
    public int getCatId() {
        return  17;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
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
        return "EntityChaseMaxDurationChance";
    }
}
