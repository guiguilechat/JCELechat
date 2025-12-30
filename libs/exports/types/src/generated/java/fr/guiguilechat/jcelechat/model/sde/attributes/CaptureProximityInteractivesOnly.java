package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * to allow capture point proximity sensors to also detect non-interactives (like NPCs/entities) 
 */
public class CaptureProximityInteractivesOnly
    extends IntAttribute
{
    public static final CaptureProximityInteractivesOnly INSTANCE = new CaptureProximityInteractivesOnly();

    @Override
    public int getId() {
        return  5602;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public Number getDefaultValue() {
        return  1.0;
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
        return "CaptureProximityInteractivesOnly";
    }
}
