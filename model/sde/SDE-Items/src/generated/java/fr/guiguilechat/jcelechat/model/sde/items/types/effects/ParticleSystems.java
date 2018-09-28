package fr.guiguilechat.jcelechat.model.sde.items.types.effects;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Effects;
import org.yaml.snakeyaml.Yaml;

public class ParticleSystems
    extends Effects
{
    public final static ParticleSystems.MetaGroup METAGROUP = new ParticleSystems.MetaGroup();

    @Override
    public IMetaGroup<ParticleSystems> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<ParticleSystems>
    {
        public final static String RESOURCE_PATH = "SDE/items/effects/ParticleSystems.yaml";
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
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(ParticleSystems.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, ParticleSystems> items;
        }
    }
}