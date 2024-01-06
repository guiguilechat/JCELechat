package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class CynosuralFieldSpawnRadius
    extends IntAttribute
{
    public static final CynosuralFieldSpawnRadius INSTANCE = new CynosuralFieldSpawnRadius();

    @Override
    public int getId() {
        return  2455;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
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
        return "CynosuralFieldSpawnRadius";
    }
}
