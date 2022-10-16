package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Bonus to optimal range of Codebreakers and Analyzers
 */
public class MiniProfessionRangeBonus
    extends IntAttribute
{
    public static final MiniProfessionRangeBonus INSTANCE = new MiniProfessionRangeBonus();

    @Override
    public int getId() {
        return  1838;
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
        return "MiniProfessionRangeBonus";
    }
}
