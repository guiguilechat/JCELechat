package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Pilot's Crimewatch sec status. Copied from character stats when boarding a ship.
 */
public class PilotSecurityStatus
    extends IntAttribute
{
    public static final PilotSecurityStatus INSTANCE = new PilotSecurityStatus();

    @Override
    public int getId() {
        return  2610;
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
        return "PilotSecurityStatus";
    }
}
