package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Display name is misleading, this attribute is used only for Shield and Armor, but for convenience sake only this one is used.
 */
public class ResistanceKiller
    extends IntAttribute
{
    public final static ResistanceKiller INSTANCE = new ResistanceKiller();

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
}
