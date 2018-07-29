package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Determines the maximum security class that a module can be onlined within. Used for structure modules.
 * 
 *  0=Nullsec
 *  1=Lowsec
 *  2=Highsec
 */
public class OnlineMaxSecurityClass
    extends IntAttribute
{
    public final static OnlineMaxSecurityClass INSTANCE = new OnlineMaxSecurityClass();

    @Override
    public int getId() {
        return  2581;
    }

    @Override
    public int getCatId() {
        return  7;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  2.0;
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
        return "OnlineMaxSecurityClass";
    }
}
