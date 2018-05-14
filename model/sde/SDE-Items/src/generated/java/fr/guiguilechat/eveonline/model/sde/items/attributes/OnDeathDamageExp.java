package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Explosive Damage when the ship dies
 */
public class OnDeathDamageExp
    extends IntAttribute
{
    public final static OnDeathDamageExp INSTANCE = new OnDeathDamageExp();

    @Override
    public int getId() {
        return  2274;
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
