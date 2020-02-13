package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The % of battleship assembly cost a player has to pay to assemble a battleship
 */
public class BattleshipConstructionCost
    extends IntAttribute
{
    public static final BattleshipConstructionCost INSTANCE = new BattleshipConstructionCost();

    @Override
    public int getId() {
        return  393;
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

    @Override
    public String toString() {
        return "BattleshipConstructionCost";
    }
}
