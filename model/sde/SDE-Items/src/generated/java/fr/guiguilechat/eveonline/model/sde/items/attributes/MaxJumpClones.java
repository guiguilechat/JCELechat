package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * The maximum amount of jump clones that the character may have in existence or ship may have stored.
 */
public class MaxJumpClones
    extends IntAttribute
{
    public final static MaxJumpClones INSTANCE = new MaxJumpClones();

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
}
