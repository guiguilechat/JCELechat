package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Skill bonus per level to manufacturing time efficiency. Only applies to skills required to manufacture the blueprint.
 */
public class ManufactureTimePerLevel
    extends IntAttribute
{
    public final static ManufactureTimePerLevel INSTANCE = new ManufactureTimePerLevel();

    @Override
    public int getId() {
        return  1982;
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
}
