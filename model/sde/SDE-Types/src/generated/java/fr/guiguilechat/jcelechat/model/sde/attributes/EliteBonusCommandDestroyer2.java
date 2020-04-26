package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class EliteBonusCommandDestroyer2
    extends IntAttribute
{
    public static final EliteBonusCommandDestroyer2 INSTANCE = new EliteBonusCommandDestroyer2();

    @Override
    public int getId() {
        return  2060;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "EliteBonusCommandDestroyer2";
    }
}
