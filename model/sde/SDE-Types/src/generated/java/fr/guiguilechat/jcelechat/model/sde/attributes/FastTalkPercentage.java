package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * a percentage multiplyer to sec status recovery
 */
public class FastTalkPercentage
    extends IntAttribute
{
    public static final FastTalkPercentage INSTANCE = new FastTalkPercentage();

    @Override
    public int getId() {
        return  359;
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
        return  100.0;
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
        return "FastTalkPercentage";
    }
}
