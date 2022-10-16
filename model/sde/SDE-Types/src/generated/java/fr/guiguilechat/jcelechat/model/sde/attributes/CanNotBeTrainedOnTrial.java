package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * If set to 1 then this skill can not be trained on accounts that are marked as Alpha Clone. Any other value (although you should probably use 0) will result in all accounts being able to train this skill.
 */
public class CanNotBeTrainedOnTrial
    extends IntAttribute
{
    public static final CanNotBeTrainedOnTrial INSTANCE = new CanNotBeTrainedOnTrial();

    @Override
    public int getId() {
        return  1047;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  1.0;
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
        return "CanNotBeTrainedOnTrial";
    }
}
