package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Salvage drone access difficult bonus attribute for salvage drone specialization skill
 */
public class SpecAccessDifficultyBonus
    extends IntAttribute
{
    public static final SpecAccessDifficultyBonus INSTANCE = new SpecAccessDifficultyBonus();

    @Override
    public int getId() {
        return  3110;
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
        return true;
    }

    @Override
    public String toString() {
        return "SpecAccessDifficultyBonus";
    }
}
