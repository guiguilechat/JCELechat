package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * second bonus for support cruisers
 */
public class EliteBonusLogistics2
    extends IntAttribute
{
    public static final EliteBonusLogistics2 INSTANCE = new EliteBonusLogistics2();

    @Override
    public int getId() {
        return  679;
    }

    @Override
    public int getCatId() {
        return  9;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
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
        return "EliteBonusLogistics2";
    }
}
