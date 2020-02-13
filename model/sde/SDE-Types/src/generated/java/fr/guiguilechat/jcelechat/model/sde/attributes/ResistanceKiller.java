package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Display name is misleading, this attribute is used only for Shield and Armor, but for convenience sake only this one is used.
 */
public class ResistanceKiller
    extends IntAttribute
{
    public static final ResistanceKiller INSTANCE = new ResistanceKiller();

    @Override
    public int getId() {
        return  1978;
    }

    @Override
    public int getCatId() {
        return  0;
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
        return "ResistanceKiller";
    }
}
