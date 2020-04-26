package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Length of No Jump Or Cloak time.
 */
public class DoomsdayNoJumpOrCloakDuration
    extends IntAttribute
{
    public static final DoomsdayNoJumpOrCloakDuration INSTANCE = new DoomsdayNoJumpOrCloakDuration();

    @Override
    public int getId() {
        return  2427;
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
