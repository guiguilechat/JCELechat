package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * A temporary attribute for projectile/hybrid weapons to indicate which charges they have loaded when created in newbie ships ala ammo.
 */
public class AmmoLoaded
    extends IntAttribute
{
    public static final AmmoLoaded INSTANCE = new AmmoLoaded();

    @Override
    public int getId() {
        return  127;
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
        return "AmmoLoaded";
    }
}
