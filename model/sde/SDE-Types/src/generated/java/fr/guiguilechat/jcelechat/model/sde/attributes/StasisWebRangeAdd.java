package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Bonus added to stasis webifier range
 */
public class StasisWebRangeAdd
    extends IntAttribute
{
    public static final StasisWebRangeAdd INSTANCE = new StasisWebRangeAdd();

    @Override
    public int getId() {
        return  3206;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "StasisWebRangeAdd";
    }
}
