package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Hidden Attribute for tech 2 launcher damage bonus.
 */
public class HiddenLauncherDamageBonus
    extends IntAttribute
{
    public static final HiddenLauncherDamageBonus INSTANCE = new HiddenLauncherDamageBonus();

    @Override
    public int getId() {
        return  845;
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
        return true;
    }

    @Override
    public String toString() {
        return "HiddenLauncherDamageBonus";
    }
}
