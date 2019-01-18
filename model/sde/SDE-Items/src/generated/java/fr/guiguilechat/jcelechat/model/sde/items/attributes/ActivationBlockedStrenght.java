package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * 
 */
public class ActivationBlockedStrenght
    extends IntAttribute
{
    public static final ActivationBlockedStrenght INSTANCE = new ActivationBlockedStrenght();

    @Override
    public int getId() {
        return  1350;
    }

    @Override
    public int getCatId() {
        return  27;
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
        return "ActivationBlockedStrenght";
    }
}
