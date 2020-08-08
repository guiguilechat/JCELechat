package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Fall Off for NPC Target Jam
 */
public class ECMRangeFalloff
    extends IntAttribute
{
    public static final ECMRangeFalloff INSTANCE = new ECMRangeFalloff();

    @Override
    public int getId() {
        return  953;
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
        return "ECMRangeFalloff";
    }
}
