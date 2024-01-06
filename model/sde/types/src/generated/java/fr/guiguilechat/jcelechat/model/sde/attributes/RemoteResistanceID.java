package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Attribute ID of the resistance type v's this Ewar module.
 */
public class RemoteResistanceID
    extends IntAttribute
{
    public static final RemoteResistanceID INSTANCE = new RemoteResistanceID();

    @Override
    public int getId() {
        return  2138;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "RemoteResistanceID";
    }
}
