package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * How long it takes to anchor or unanchor this object.
 */
public class AnchoringDelay
    extends IntAttribute
{
    public static final AnchoringDelay INSTANCE = new AnchoringDelay();

    @Override
    public int getId() {
        return  556;
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
        return "AnchoringDelay";
    }
}
