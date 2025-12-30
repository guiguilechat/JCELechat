package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;

public class StructureEntities
    extends Entity
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final StructureEntities.MetaGroup METAGROUP = new StructureEntities.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<StructureEntities> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<StructureEntities>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/StructureEntities.yaml";
        private Map<Integer, StructureEntities> cache = (null);

        @Override
        public IMetaCategory<? super StructureEntities> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1872;
        }

        @Override
        public String getName() {
            return "StructureEntities";
        }

        @Override
        public synchronized Map<Integer, StructureEntities> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(StructureEntities.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, StructureEntities> types;
        }
    }
}
