package fr.guiguilechat.jcelechat.model.sde.types.effects;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Effects;
import org.yaml.snakeyaml.Yaml;

public class ParticleSystems
    extends Effects
{
    public static final ParticleSystems.MetaGroup METAGROUP = new ParticleSystems.MetaGroup();

    @Override
    public IMetaGroup<ParticleSystems> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<ParticleSystems>
    {
        public static final String RESOURCE_PATH = "SDE/types/effects/ParticleSystems.yaml";
        private Map<String, ParticleSystems> cache = (null);

        @Override
        public IMetaCategory<? super ParticleSystems> category() {
            return Effects.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1107;
        }

        @Override
        public String getName() {
            return "ParticleSystems";
        }

        @Override
        public synchronized Map<String, ParticleSystems> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(ParticleSystems.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, ParticleSystems> types;
        }
    }
}
