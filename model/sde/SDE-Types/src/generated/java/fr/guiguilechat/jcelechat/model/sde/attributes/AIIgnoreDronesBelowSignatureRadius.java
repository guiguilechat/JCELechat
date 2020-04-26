package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * NPC'S with this attribute wont shoot drones with signature radius less than this value.
 */
public class AIIgnoreDronesBelowSignatureRadius
    extends IntAttribute
{
    public static final AIIgnoreDronesBelowSignatureRadius INSTANCE = new AIIgnoreDronesBelowSignatureRadius();

    @Override
    public int getId() {
        return  1855;
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
        return "AIIgnoreDronesBelowSignatureRadius";
    }
}
