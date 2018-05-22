package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Chance of NPC effect to be activated each duration
 */
public class EntityTargetPaintDurationChance
    extends IntAttribute
{
    public final static EntityTargetPaintDurationChance INSTANCE = new EntityTargetPaintDurationChance();

    @Override
    public int getId() {
        return  935;
    }

    @Override
    public int getCatId() {
        return  21;
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
        return "EntityTargetPaintDurationChance";
    }
}
