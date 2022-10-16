package fr.guiguilechat.jcelechat.model.sde.types.effects;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Effects;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class LensFlares
    extends Effects
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final LensFlares.MetaGroup METAGROUP = new LensFlares.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<LensFlares> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<LensFlares>
    {
        public static final String RESOURCE_PATH = "SDE/types/effects/LensFlares.yaml";
        private Map<String, LensFlares> cache = (null);

        @Override
        public IMetaCategory<? super LensFlares> category() {
            return Effects.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1105;
        }

        @Override
        public String getName() {
            return "LensFlares";
        }

        @Override
        public synchronized Map<String, LensFlares> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(LensFlares.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<String, LensFlares> types;
        }
    }
}
