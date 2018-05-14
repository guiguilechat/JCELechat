package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Increase in Small Projectile Turret optimal range
 */
public class RookieSPTOptimal
    extends DoubleAttribute
{
    public final static RookieSPTOptimal INSTANCE = new RookieSPTOptimal();

    @Override
    public int getId() {
        return  1869;
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
