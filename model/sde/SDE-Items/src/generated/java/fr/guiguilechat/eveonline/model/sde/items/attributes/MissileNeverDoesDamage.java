package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * If present on a type which is used like a missile, signifies that it should never do damage (whether it has any to do or not).
 */
public class MissileNeverDoesDamage
    extends IntAttribute
{
    public final static MissileNeverDoesDamage INSTANCE = new MissileNeverDoesDamage();

    @Override
    public int getId() {
        return  1075;
    }

    @Override
    public int getCatId() {
        return  9;
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
        return "MissileNeverDoesDamage";
    }
}