package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Used for chance based accuracy hit calculation for entity super weapon.
 */
public class EntitySuperWeaponFallOff
    extends IntAttribute
{
    public static final EntitySuperWeaponFallOff INSTANCE = new EntitySuperWeaponFallOff();

    @Override
    public int getId() {
        return  2047;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
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
        return "EntitySuperWeaponFallOff";
    }
}
