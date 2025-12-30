package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * It is a Boolean Value that defaults to false. If true, it will only allow players to Hack if they belong to the Faction that is an enemy of the Occupier of the Star System.
 */
public class AllowOnlyFwAttackers
    extends IntAttribute
{
    public static final AllowOnlyFwAttackers INSTANCE = new AllowOnlyFwAttackers();

    @Override
    public int getId() {
        return  5206;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
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
        return false;
    }

    @Override
    public String toString() {
        return "AllowOnlyFwAttackers";
    }
}
