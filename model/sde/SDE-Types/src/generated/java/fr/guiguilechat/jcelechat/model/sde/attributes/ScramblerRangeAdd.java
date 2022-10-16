package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Bonus added to warp scrambler range
 */
public class ScramblerRangeAdd
    extends IntAttribute
{
    public static final ScramblerRangeAdd INSTANCE = new ScramblerRangeAdd();

    @Override
    public int getId() {
        return  3257;
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
        return "ScramblerRangeAdd";
    }
}
