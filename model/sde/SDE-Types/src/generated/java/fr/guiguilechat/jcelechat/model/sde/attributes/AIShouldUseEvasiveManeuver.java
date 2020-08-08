package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The evasive maneuver level of the type. this will control what types of evasive maneuvers a NPC ship will use.
 */
public class AIShouldUseEvasiveManeuver
    extends IntAttribute
{
    public static final AIShouldUseEvasiveManeuver INSTANCE = new AIShouldUseEvasiveManeuver();

    @Override
    public int getId() {
        return  1414;
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
        return "AIShouldUseEvasiveManeuver";
    }
}
