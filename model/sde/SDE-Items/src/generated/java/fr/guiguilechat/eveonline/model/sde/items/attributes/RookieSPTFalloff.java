package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Increase in Small Projectile Turret falloff
 */
public class RookieSPTFalloff
    extends DoubleAttribute
{
    public final static RookieSPTFalloff INSTANCE = new RookieSPTFalloff();

    @Override
    public int getId() {
        return  1868;
    }

    @Override
    public int getCatId() {
        return  0;
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
}
