package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class FilamentDescriptionMessageID
    extends IntAttribute
{
    public static final FilamentDescriptionMessageID INSTANCE = new FilamentDescriptionMessageID();

    @Override
    public int getId() {
        return  3026;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public Number getDefaultValue() {
        return  561098.0;
    }

    @Override
    public boolean getPublished() {
        return false;
    }

    @Override
    public boolean getStackable() {
        return false;
    }

    @Override
    public String toString() {
        return "FilamentDescriptionMessageID";
    }
}
