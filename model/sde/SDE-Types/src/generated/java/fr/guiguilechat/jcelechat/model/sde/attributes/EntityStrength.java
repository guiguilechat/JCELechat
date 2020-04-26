package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * A relative strength that indicates how powerful this NPC entity is in combat.
 */
public class EntityStrength
    extends IntAttribute
{
    public static final EntityStrength INSTANCE = new EntityStrength();

    @Override
    public int getId() {
        return  542;
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
        return "EntityStrength";
    }
}
