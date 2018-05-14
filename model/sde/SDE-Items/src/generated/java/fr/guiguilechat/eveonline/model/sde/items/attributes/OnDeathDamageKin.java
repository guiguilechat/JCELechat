package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Kinetic Damage when the ship dies
 */
public class OnDeathDamageKin
    extends IntAttribute
{
    public final static OnDeathDamageKin INSTANCE = new OnDeathDamageKin();

    @Override
    public int getId() {
        return  2273;
    }

    @Override
    public int getCatId() {
        return  41;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }
}
