package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Helper attribute for distribution dungeons.
 */
public class ScanAllStrength
    extends IntAttribute
{
    public static final ScanAllStrength INSTANCE = new ScanAllStrength();

    @Override
    public int getId() {
        return  1136;
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
        return "ScanAllStrength";
    }
}
