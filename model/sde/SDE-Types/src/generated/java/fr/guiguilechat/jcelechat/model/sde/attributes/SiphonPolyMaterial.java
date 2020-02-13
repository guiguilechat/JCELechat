package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Amount of Polymer Materials stolen from active Polymer Reactor Array every cycle. 
 */
public class SiphonPolyMaterial
    extends IntAttribute
{
    public static final SiphonPolyMaterial INSTANCE = new SiphonPolyMaterial();

    @Override
    public int getId() {
        return  1933;
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
        return "SiphonPolyMaterial";
    }
}
