package fr.guiguilechat.jcelechat.model.sde.types.commodity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Commodity;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class BountyEncryptedBonds
    extends Commodity
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final BountyEncryptedBonds.MetaGroup METAGROUP = new BountyEncryptedBonds.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<BountyEncryptedBonds> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<BountyEncryptedBonds>
    {
        public static final String RESOURCE_PATH = "SDE/types/commodity/BountyEncryptedBonds.yaml";
        private Map<Integer, BountyEncryptedBonds> cache = (null);

        @Override
        public IMetaCategory<? super BountyEncryptedBonds> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1248;
        }

        @Override
        public String getName() {
            return "BountyEncryptedBonds";
        }

        @Override
        public synchronized Map<Integer, BountyEncryptedBonds> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(BountyEncryptedBonds.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    LoaderOptions options = new LoaderOptions();
                    options.setCodePointLimit(Integer.MAX_VALUE);
                    cache = new Yaml(options).loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<Integer, BountyEncryptedBonds> types;
        }
    }
}
