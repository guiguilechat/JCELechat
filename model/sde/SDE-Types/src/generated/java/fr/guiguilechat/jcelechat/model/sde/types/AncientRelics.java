package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.types.ancientrelics.SleeperDefensiveRelics;
import fr.guiguilechat.jcelechat.model.sde.types.ancientrelics.SleeperEngineeringRelics;
import fr.guiguilechat.jcelechat.model.sde.types.ancientrelics.SleeperHullRelics;
import fr.guiguilechat.jcelechat.model.sde.types.ancientrelics.SleeperOffensiveRelics;
import fr.guiguilechat.jcelechat.model.sde.types.ancientrelics.SleeperPropulsionRelics;

public abstract class AncientRelics
    extends EveType
{
    /**
     * This is a bookkeeping attribute for blueprints, which will hopefully be deprecated by the end of 2014
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double IndustryBlueprintRank;
    public static final AncientRelics.MetaCat METACAT = new AncientRelics.MetaCat();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  1955 :
            {
                return IndustryBlueprintRank;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaCategory<AncientRelics> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<AncientRelics>
    {

        @Override
        public int getCategoryId() {
            return  34;
        }

        @Override
        public String getName() {
            return "AncientRelics";
        }

        @Override
        public Collection<IMetaGroup<? extends AncientRelics>> groups() {
            return Arrays.asList(SleeperPropulsionRelics.METAGROUP, SleeperOffensiveRelics.METAGROUP, SleeperEngineeringRelics.METAGROUP, SleeperDefensiveRelics.METAGROUP, SleeperHullRelics.METAGROUP);
        }
    }
}
