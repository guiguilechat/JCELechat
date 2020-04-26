package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The maximum number of users that can be present within the operational range of the structure for it to be capable of operation.
 */
public class MaxOperationalUsers
    extends IntAttribute
{
    public static final MaxOperationalUsers INSTANCE = new MaxOperationalUsers();

    @Override
    public int getId() {
        return  716;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "MaxOperationalUsers";
    }
}
