package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * numeric classification for tower size
 * 
 */
public class ControlTowerSize
    extends IntAttribute
{
    public static final ControlTowerSize INSTANCE = new ControlTowerSize();

    @Override
    public int getId() {
        return  1031;
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
        return "ControlTowerSize";
    }
}
