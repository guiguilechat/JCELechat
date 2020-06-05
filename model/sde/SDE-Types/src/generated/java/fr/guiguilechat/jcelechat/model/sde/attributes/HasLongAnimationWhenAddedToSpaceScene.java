package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The item with this attribute set to 1 keeps track of when added to space, and puts that on the slim item, but if it was before downtime the slim item value gets set to -1. Created for supporting long animations upon adding to space.
 */
public class HasLongAnimationWhenAddedToSpaceScene
    extends IntAttribute
{
    public static final HasLongAnimationWhenAddedToSpaceScene INSTANCE = new HasLongAnimationWhenAddedToSpaceScene();

    @Override
    public int getId() {
        return  2827;
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
        return "HasLongAnimationWhenAddedToSpaceScene";
    }
}
