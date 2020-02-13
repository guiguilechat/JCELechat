package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Stays with characters across clone changes. ONLY FOR IMPLANTS AND BOOSTERS.
 */
public class FollowsJumpClones
    extends IntAttribute
{
    public static final FollowsJumpClones INSTANCE = new FollowsJumpClones();

    @Override
    public int getId() {
        return  1916;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "FollowsJumpClones";
    }
}
