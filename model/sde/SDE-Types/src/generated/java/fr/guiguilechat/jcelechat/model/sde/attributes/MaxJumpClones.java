package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The maximum amount of jump clones that the character may have in existence or ship may have stored.
 */
public class MaxJumpClones
    extends IntAttribute
{
    public static final MaxJumpClones INSTANCE = new MaxJumpClones();

    @Override
    public int getId() {
        return  979;
    }

    @Override
    public int getCatId() {
        return  7;
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
        return "MaxJumpClones";
    }
}
