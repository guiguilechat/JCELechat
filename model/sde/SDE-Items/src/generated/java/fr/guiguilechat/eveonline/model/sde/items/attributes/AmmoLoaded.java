package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * A temporary attribute for projectile/hybrid weapons to indicate which charges they have loaded when created in newbie ships ala ammo.
 */
public class AmmoLoaded
    extends IntAttribute
{
    public final static AmmoLoaded INSTANCE = new AmmoLoaded();

    @Override
    public int getId() {
        return  127;
    }

    @Override
    public int getCatId() {
        return  7;
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
