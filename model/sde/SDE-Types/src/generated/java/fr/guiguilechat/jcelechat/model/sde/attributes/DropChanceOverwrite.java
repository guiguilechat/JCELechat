package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * A attribute meant to over write the chance of loot dropping from a ship, so a setting of 0 will lead to no loot dropping from the player ship.
 */
public class DropChanceOverwrite
    extends IntAttribute
{
    public static final DropChanceOverwrite INSTANCE = new DropChanceOverwrite();

    @Override
    public int getId() {
        return  3164;
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
        return "DropChanceOverwrite";
    }
}
