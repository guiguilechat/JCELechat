package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class SurveyProbeDurationBonus
    extends IntAttribute
{
    public static final SurveyProbeDurationBonus INSTANCE = new SurveyProbeDurationBonus();

    @Override
    public int getId() {
        return  2701;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "SurveyProbeDurationBonus";
    }
}
