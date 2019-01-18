package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class ArtifactsAndPrototypes
    extends Commodity
{
    public static final ArtifactsAndPrototypes.MetaGroup METAGROUP = new ArtifactsAndPrototypes.MetaGroup();

    @Override
    public IMetaGroup<ArtifactsAndPrototypes> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<ArtifactsAndPrototypes>
    {
        public static final String RESOURCE_PATH = "SDE/items/commodity/ArtifactsAndPrototypes.yaml";
        private Map<String, ArtifactsAndPrototypes> cache = (null);

        @Override
        public IMetaCategory<? super ArtifactsAndPrototypes> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  528;
        }

        @Override
        public String getName() {
            return "ArtifactsAndPrototypes";
        }

        @Override
        public synchronized Map<String, ArtifactsAndPrototypes> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(ArtifactsAndPrototypes.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, ArtifactsAndPrototypes> items;
        }
    }
}
