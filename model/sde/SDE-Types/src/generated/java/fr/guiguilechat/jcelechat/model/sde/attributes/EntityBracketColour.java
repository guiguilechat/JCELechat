package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 *  0: white (default)
 *  1: red (hostile NPC)
 *  2: blue (Neutral NPC)
 */
public class EntityBracketColour
    extends IntAttribute
{
    public static final EntityBracketColour INSTANCE = new EntityBracketColour();

    @Override
    public int getId() {
        return  798;
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
        return "EntityBracketColour";
    }
}
