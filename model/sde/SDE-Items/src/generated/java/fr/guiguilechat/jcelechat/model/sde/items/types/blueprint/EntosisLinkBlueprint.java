package fr.guiguilechat.jcelechat.model.sde.items.types.blueprint;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Blueprint;
import org.yaml.snakeyaml.Yaml;

public class EntosisLinkBlueprint
    extends Blueprint
{
    /**
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int TechLevel;
    public final static EntosisLinkBlueprint.MetaGroup METAGROUP = new EntosisLinkBlueprint.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/blueprint/EntosisLinkBlueprint.yaml";
    private static Map<String, EntosisLinkBlueprint> cache = (null);

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  422 :
            {
                return TechLevel;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public int getGroupId() {
        return  1318;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<EntosisLinkBlueprint> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, EntosisLinkBlueprint> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(EntosisLinkBlueprint.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, EntosisLinkBlueprint> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<EntosisLinkBlueprint>
    {

        @Override
        public MetaCategory<? super EntosisLinkBlueprint> category() {
            return Blueprint.METACAT;
        }

        @Override
        public String getName() {
            return "EntosisLinkBlueprint";
        }

        @Override
        public Collection<EntosisLinkBlueprint> items() {
            return (load().values());
        }
    }
}
