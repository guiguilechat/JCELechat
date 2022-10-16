package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * sets the difficulty tier for abyssal deadspace keys
 */
public class DifficultyTier
    extends IntAttribute
{
    public static final DifficultyTier INSTANCE = new DifficultyTier();

    @Override
    public int getId() {
        return  2761;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  1.0;
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
        return "DifficultyTier";
    }
}
