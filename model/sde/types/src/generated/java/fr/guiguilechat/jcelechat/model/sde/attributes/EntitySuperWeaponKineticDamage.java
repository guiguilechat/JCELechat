package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Used for NPCs to replicate damage for the super weapon.
 */
public class EntitySuperWeaponKineticDamage
    extends IntAttribute
{
    public static final EntitySuperWeaponKineticDamage INSTANCE = new EntitySuperWeaponKineticDamage();

    @Override
    public int getId() {
        return  2011;
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
        return "EntitySuperWeaponKineticDamage";
    }
}
