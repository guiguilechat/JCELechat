package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * attribute that, along with aspectRatioHeight, describes the proportions of videos/images in a puzzle
 */
public class AspectRatioWidth
    extends IntAttribute
{
    public static final AspectRatioWidth INSTANCE = new AspectRatioWidth();

    @Override
    public int getId() {
        return  5759;
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
        return "AspectRatioWidth";
    }
}
