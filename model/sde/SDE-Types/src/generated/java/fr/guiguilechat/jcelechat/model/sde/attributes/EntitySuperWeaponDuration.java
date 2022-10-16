package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Used for NPCs to replicate cooldown functionality for the super weapon.
 */
public class EntitySuperWeaponDuration
    extends IntAttribute
{
    public static final EntitySuperWeaponDuration INSTANCE = new EntitySuperWeaponDuration();

    @Override
    public int getId() {
        return  2009;
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
        return "EntitySuperWeaponDuration";
    }
}
