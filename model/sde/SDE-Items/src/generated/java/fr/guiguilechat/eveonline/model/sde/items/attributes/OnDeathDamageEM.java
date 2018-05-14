package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * EM Damage when the ship dies
 */
public class OnDeathDamageEM
    extends IntAttribute
{
    public final static OnDeathDamageEM INSTANCE = new OnDeathDamageEM();

    @Override
    public int getId() {
        return  2271;
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
