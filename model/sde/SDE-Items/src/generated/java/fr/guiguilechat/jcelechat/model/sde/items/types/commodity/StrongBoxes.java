package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class StrongBoxes
    extends Commodity
{
    /**
     * Authoring has been moved to FSD.
     * meta group of type
     * 
     *  3: Story-line (Cosmos)
     *  4: Faction
     *  5: Officer (rare asteroid NPCs)
     *  6: Deadspace
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MetaGroupID;
    public static final StrongBoxes.MetaGroup METAGROUP = new StrongBoxes.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  1692 :
            {
                return MetaGroupID;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<StrongBoxes> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<StrongBoxes>
    {
        public static final String RESOURCE_PATH = "SDE/items/commodity/StrongBoxes.yaml";
        private Map<String, StrongBoxes> cache = (null);

        @Override
        public IMetaCategory<? super StrongBoxes> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1818;
        }

        @Override
        public String getName() {
            return "StrongBoxes";
        }

        @Override
        public synchronized Map<String, StrongBoxes> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(StrongBoxes.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, StrongBoxes> items;
        }
    }
}
