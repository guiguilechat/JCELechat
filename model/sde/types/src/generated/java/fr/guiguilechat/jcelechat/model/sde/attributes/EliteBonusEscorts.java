package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * bonus for escort class frigates
 */
public class EliteBonusEscorts
    extends IntAttribute
{
    public static final EliteBonusEscorts INSTANCE = new EliteBonusEscorts();

    @Override
    public int getId() {
        return  597;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  10.0;
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
        return "EliteBonusEscorts";
    }
}
