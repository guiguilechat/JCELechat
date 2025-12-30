package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * fourth bonus for support cruisers
 */
public class EliteBonusLogistics4
    extends RealAttribute
{
    public static final EliteBonusLogistics4 INSTANCE = new EliteBonusLogistics4();

    @Override
    public int getId() {
        return  5792;
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
        return "EliteBonusLogistics4";
    }
}
