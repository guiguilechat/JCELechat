package fr.guiguilechat.jcelechat.model.sde.items.types.blueprint;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Blueprint;
import org.yaml.snakeyaml.Yaml;

public class SpecialEditionCommodityBlueprints
    extends Blueprint
{
    /**
     * This is a bookkeeping attribute for blueprints, which will hopefully be deprecated by the end of 2014
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double IndustryBlueprintRank;
    public final static SpecialEditionCommodityBlueprints.MetaGroup METAGROUP = new SpecialEditionCommodityBlueprints.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/blueprint/SpecialEditionCommodityBlueprints.yaml";
    private static Map<String, SpecialEditionCommodityBlueprints> cache = (null);

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
    public int getGroupId() {
        return  1197;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<SpecialEditionCommodityBlueprints> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, SpecialEditionCommodityBlueprints> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(SpecialEditionCommodityBlueprints.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, SpecialEditionCommodityBlueprints> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<SpecialEditionCommodityBlueprints>
    {

        @Override
        public MetaCategory<? super SpecialEditionCommodityBlueprints> category() {
            return Blueprint.METACAT;
        }

        @Override
        public String getName() {
            return "SpecialEditionCommodityBlueprints";
        }

        @Override
        public Collection<SpecialEditionCommodityBlueprints> items() {
            return (load().values());
        }
    }
}
