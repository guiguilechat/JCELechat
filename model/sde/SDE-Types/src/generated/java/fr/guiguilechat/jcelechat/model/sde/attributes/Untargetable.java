package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Attribute to disallow targetting.
 */
public class Untargetable
    extends IntAttribute
{
    public static final Untargetable INSTANCE = new Untargetable();

    @Override
    public int getId() {
        return  1158;
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
        return "Untargetable";
    }
}
