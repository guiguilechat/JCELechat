package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * The amount of milliseconds before the wreck dissapears. Note: this only applies to NPC wrecks or empty player wrecks.
 */
public class ExplosionDelayWreck
    extends IntAttribute
{
    public final static ExplosionDelayWreck INSTANCE = new ExplosionDelayWreck();

    @Override
    public int getId() {
        return  1162;
    }

    @Override
    public int getCatId() {
        return  9;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  7200000.0;
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
        return "ExplosionDelayWreck";
    }
}
