package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * if the module is disallowed in low sec (empire space), if it also have this attribute, it will allow that module to be used in low sec system if the systems is fully corrupted
 */
public class AllowInFullyCorruptedLowSec
    extends IntAttribute
{
    public static final AllowInFullyCorruptedLowSec INSTANCE = new AllowInFullyCorruptedLowSec();

    @Override
    public int getId() {
        return  5599;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
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
        return false;
    }

    @Override
    public String toString() {
        return "AllowInFullyCorruptedLowSec";
    }
}
