package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


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
    public static final OnlineMaxSecurityClass INSTANCE = new OnlineMaxSecurityClass();

    @Override
    public int getId() {
        return  2581;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
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
