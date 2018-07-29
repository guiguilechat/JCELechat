package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * The maximum number of users that can be present within the operational range of the structure for it to be capable of operation.
 */
public class MaxOperationalUsers
    extends IntAttribute
{
    public final static MaxOperationalUsers INSTANCE = new MaxOperationalUsers();

    @Override
    public int getId() {
        return  716;
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
