package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * when authored alongside the effectTractorBeamCan it will determine if it only tractors corpses instead of wrecks and cans
 */
public class OnlyTractorCorpses
    extends IntAttribute
{
    public static final OnlyTractorCorpses INSTANCE = new OnlyTractorCorpses();

    @Override
    public int getId() {
        return  3102;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
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
        return false;
    }

    @Override
    public String toString() {
        return "OnlyTractorCorpses";
    }
}
