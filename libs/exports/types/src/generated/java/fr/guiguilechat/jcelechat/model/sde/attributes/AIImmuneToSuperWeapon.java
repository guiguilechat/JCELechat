package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Can not be attacked by doomsday devices
 */
public class AIImmuneToSuperWeapon
    extends IntAttribute
{
    public static final AIImmuneToSuperWeapon INSTANCE = new AIImmuneToSuperWeapon();

    @Override
    public int getId() {
        return  1654;
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
        return "AIImmuneToSuperWeapon";
    }
}
