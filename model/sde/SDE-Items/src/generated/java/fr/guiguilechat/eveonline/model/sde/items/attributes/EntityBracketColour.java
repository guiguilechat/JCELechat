package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 *  0: white (default)
 *  1: red (hostile NPC)
 *  2: blue (Neutral NPC)
 */
public class EntityBracketColour
    extends IntAttribute
{
    public final static EntityBracketColour INSTANCE = new EntityBracketColour();

    @Override
    public int getId() {
        return  798;
    }

    @Override
    public int getCatId() {
        return  31;
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
}
