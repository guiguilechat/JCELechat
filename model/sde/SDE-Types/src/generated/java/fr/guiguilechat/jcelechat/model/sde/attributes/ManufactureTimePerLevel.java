package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Skill bonus per level to manufacturing time efficiency. Only applies to skills required to manufacture the blueprint.
 */
public class ManufactureTimePerLevel
    extends IntAttribute
{
    public static final ManufactureTimePerLevel INSTANCE = new ManufactureTimePerLevel();

    @Override
    public int getId() {
        return  1982;
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
        return "ManufactureTimePerLevel";
    }
}
