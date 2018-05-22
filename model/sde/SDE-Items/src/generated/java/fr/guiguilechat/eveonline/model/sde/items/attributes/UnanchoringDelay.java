package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * How long it takes to unanchor this object.
 */
public class UnanchoringDelay
    extends IntAttribute
{
    public final static UnanchoringDelay INSTANCE = new UnanchoringDelay();

    @Override
    public int getId() {
        return  676;
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
        return  60000.0;
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
        return "UnanchoringDelay";
    }
}
