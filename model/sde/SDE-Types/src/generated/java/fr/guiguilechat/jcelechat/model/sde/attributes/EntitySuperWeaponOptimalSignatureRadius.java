package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Used for chance based accuracy hit calculation for entity super weapon.
 */
public class EntitySuperWeaponOptimalSignatureRadius
    extends IntAttribute
{
    public static final EntitySuperWeaponOptimalSignatureRadius INSTANCE = new EntitySuperWeaponOptimalSignatureRadius();

    @Override
    public int getId() {
        return  2049;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  20.0;
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
        return "EntitySuperWeaponOptimalSignatureRadius";
    }
}
