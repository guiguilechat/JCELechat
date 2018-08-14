package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.ancientrelics.SleeperDefensiveRelics;
import fr.guiguilechat.jcelechat.model.sde.items.types.ancientrelics.SleeperEngineeringRelics;
import fr.guiguilechat.jcelechat.model.sde.items.types.ancientrelics.SleeperHullRelics;
import fr.guiguilechat.jcelechat.model.sde.items.types.ancientrelics.SleeperOffensiveRelics;
import fr.guiguilechat.jcelechat.model.sde.items.types.ancientrelics.SleeperPropulsionRelics;

public abstract class AncientRelics
    extends Item
{
    /**
     * This is a bookkeeping attribute for blueprints, which will hopefully be deprecated by the end of 2014
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double IndustryBlueprintRank;
    public final static AncientRelics.MetaCat METACAT = new AncientRelics.MetaCat();

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
        @SuppressWarnings("unchecked")
        private final static IMetaGroup<? extends AncientRelics> [] groups = new IMetaGroup[] {SleeperPropulsionRelics.METAGROUP, SleeperOffensiveRelics.METAGROUP, SleeperEngineeringRelics.METAGROUP, SleeperDefensiveRelics.METAGROUP, SleeperHullRelics.METAGROUP };

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
            return Arrays.asList(groups);
        }

        @Override
        public Map<String, AncientRelics> load() {
            HashMap<String, AncientRelics> ret = new HashMap<>();
            groups().stream().flatMap(img -> img.load().entrySet().stream()).forEach(e -> ret.put(e.getKey(), e.getValue()));
            return ret;
        }
    }
}
