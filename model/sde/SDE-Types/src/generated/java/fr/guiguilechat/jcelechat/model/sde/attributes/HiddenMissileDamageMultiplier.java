package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Missile damage attribute used by structures as a workaround for implementing Standup BCS stacking penalties
 */
public class HiddenMissileDamageMultiplier
    extends IntAttribute
{
    public static final HiddenMissileDamageMultiplier INSTANCE = new HiddenMissileDamageMultiplier();

    @Override
    public int getId() {
        return  2750;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
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

    @Override
    public String toString() {
        return "HiddenMissileDamageMultiplier";
    }
}
