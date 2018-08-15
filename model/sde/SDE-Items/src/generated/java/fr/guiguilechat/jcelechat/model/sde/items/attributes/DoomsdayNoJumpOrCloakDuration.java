package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Length of No Jump Or Cloak time.
 */
public class DoomsdayNoJumpOrCloakDuration
    extends IntAttribute
{
    public final static DoomsdayNoJumpOrCloakDuration INSTANCE = new DoomsdayNoJumpOrCloakDuration();

    @Override
    public int getId() {
        return  2427;
    }

    @Override
    public int getCatId() {
        return  39;
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
        return "DoomsdayNoJumpOrCloakDuration";
    }
}