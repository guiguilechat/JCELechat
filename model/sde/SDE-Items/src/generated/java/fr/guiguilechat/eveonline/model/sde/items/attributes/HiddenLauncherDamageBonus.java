package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Hidden Attribute for tech 2 launcher damage bonus.
 */
public class HiddenLauncherDamageBonus
    extends IntAttribute
{
    public final static HiddenLauncherDamageBonus INSTANCE = new HiddenLauncherDamageBonus();

    @Override
    public int getId() {
        return  845;
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
