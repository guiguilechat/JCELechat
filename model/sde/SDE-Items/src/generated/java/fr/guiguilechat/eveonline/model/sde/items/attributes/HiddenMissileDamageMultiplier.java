package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Missile damage attribute used by structures as a workaround for implementing Standup BCS stacking penalties
 */
public class HiddenMissileDamageMultiplier
    extends IntAttribute
{
    public final static HiddenMissileDamageMultiplier INSTANCE = new HiddenMissileDamageMultiplier();

    @Override
    public int getId() {
        return  2750;
    }

    @Override
    public int getCatId() {
        return  30;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  1.0;
    }

    @Override
    public boolean getPublished() {
        return false;
    }

    @Override
    public boolean getStackable() {
        return false;
    }
}
