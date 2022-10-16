package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class RemoteArmorRepairerFalloffBonus
    extends IntAttribute
{
    public static final RemoteArmorRepairerFalloffBonus INSTANCE = new RemoteArmorRepairerFalloffBonus();

    @Override
    public int getId() {
        return  2694;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
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

    @Override
    public String toString() {
        return "RemoteArmorRepairerFalloffBonus";
    }
}
