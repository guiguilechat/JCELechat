package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * eliteBonusJumpFreighter1
 */
public class EliteBonusJumpFreighter1
    extends IntAttribute
{
    public static final EliteBonusJumpFreighter1 INSTANCE = new EliteBonusJumpFreighter1();

    @Override
    public int getId() {
        return  1311;
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
        return "EliteBonusJumpFreighter1";
    }
}
