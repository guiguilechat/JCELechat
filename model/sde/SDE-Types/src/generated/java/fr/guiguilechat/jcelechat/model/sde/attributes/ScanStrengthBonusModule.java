package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class ScanStrengthBonusModule
    extends IntAttribute
{
    public static final ScanStrengthBonusModule INSTANCE = new ScanStrengthBonusModule();

    @Override
    public int getId() {
        return  1907;
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
        return false;
    }

    @Override
    public String toString() {
        return "ScanStrengthBonusModule";
    }
}
