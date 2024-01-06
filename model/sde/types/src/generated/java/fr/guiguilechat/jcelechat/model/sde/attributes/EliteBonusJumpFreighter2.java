package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * eliteBonusJumpFreighter2
 */
public class EliteBonusJumpFreighter2
    extends IntAttribute
{
    public static final EliteBonusJumpFreighter2 INSTANCE = new EliteBonusJumpFreighter2();

    @Override
    public int getId() {
        return  1312;
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
        return "EliteBonusJumpFreighter2";
    }
}
