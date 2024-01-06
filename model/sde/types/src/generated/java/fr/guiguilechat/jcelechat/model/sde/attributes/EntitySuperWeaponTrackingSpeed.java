package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Used for chance based accuracy hit calculation for entity super weapon.
 */
public class EntitySuperWeaponTrackingSpeed
    extends RealAttribute
{
    public static final EntitySuperWeaponTrackingSpeed INSTANCE = new EntitySuperWeaponTrackingSpeed();

    @Override
    public int getId() {
        return  2048;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "EntitySuperWeaponTrackingSpeed";
    }
}
