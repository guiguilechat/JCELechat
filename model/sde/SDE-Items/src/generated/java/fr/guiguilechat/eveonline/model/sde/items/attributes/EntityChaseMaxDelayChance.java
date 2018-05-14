package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Chance that the max delay is waited before chase is engaged.
 */
public class EntityChaseMaxDelayChance
    extends IntAttribute
{
    public final static EntityChaseMaxDelayChance INSTANCE = new EntityChaseMaxDelayChance();

    @Override
    public int getId() {
        return  581;
    }

    @Override
    public int getCatId() {
        return  17;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  1.0;
    }

    @Override
    public boolean getPublished() {
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }
}
