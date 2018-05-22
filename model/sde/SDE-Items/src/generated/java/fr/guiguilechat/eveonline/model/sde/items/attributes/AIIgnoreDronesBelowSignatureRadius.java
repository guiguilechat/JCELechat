package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * NPC'S with this attribute wont shoot drones with signature radius less than this value.
 */
public class AIIgnoreDronesBelowSignatureRadius
    extends IntAttribute
{
    public final static AIIgnoreDronesBelowSignatureRadius INSTANCE = new AIIgnoreDronesBelowSignatureRadius();

    @Override
    public int getId() {
        return  1855;
    }

    @Override
    public int getCatId() {
        return  12;
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
