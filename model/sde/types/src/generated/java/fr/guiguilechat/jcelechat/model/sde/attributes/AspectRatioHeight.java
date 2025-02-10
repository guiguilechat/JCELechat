package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * attribute that, along with aspectRatioWidth, describes the proportions of videos/images in a puzzle
 */
public class AspectRatioHeight
    extends IntAttribute
{
    public static final AspectRatioHeight INSTANCE = new AspectRatioHeight();

    @Override
    public int getId() {
        return  5760;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public Number getDefaultValue() {
        return  1.0;
    }

    @Override
    public boolean getPublished() {
        return false;
    }

    @Override
    public boolean getStackable() {
        return false;
    }

    @Override
    public String toString() {
        return "AspectRatioHeight";
    }
}
