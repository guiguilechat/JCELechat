package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Used for chance based accuracy hit calculation for entity super weapon.
 */
public class EntitySuperWeaponMaxRange
    extends IntAttribute
{
    public static final EntitySuperWeaponMaxRange INSTANCE = new EntitySuperWeaponMaxRange();

    @Override
    public int getId() {
        return  2046;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  250000.0;
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
        return "EntitySuperWeaponMaxRange";
    }
}
