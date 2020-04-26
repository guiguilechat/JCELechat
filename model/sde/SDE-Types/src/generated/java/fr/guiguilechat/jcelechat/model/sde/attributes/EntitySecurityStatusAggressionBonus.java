package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The amount of security status lost of aggressing agaisnt this entity first.
 */
public class EntitySecurityStatusAggressionBonus
    extends IntAttribute
{
    public static final EntitySecurityStatusAggressionBonus INSTANCE = new EntitySecurityStatusAggressionBonus();

    @Override
    public int getId() {
        return  253;
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
        return "EntitySecurityStatusAggressionBonus";
    }
}
