package fr.guiguilechat.jcelechat.model.sde.types.decryptors;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.InventionMEModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.InventionMaxRunModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.InventionPropabilityMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.InventionTEModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.types.Decryptors;
import org.yaml.snakeyaml.Yaml;

public class GenericDecryptor
    extends Decryptors
{
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {Radius.INSTANCE, InventionMaxRunModifier.INSTANCE, Capacity.INSTANCE, InventionPropabilityMultiplier.INSTANCE, InventionMEModifier.INSTANCE, InventionTEModifier.INSTANCE })));
    public static final GenericDecryptor.MetaGroup METAGROUP = new GenericDecryptor.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<GenericDecryptor> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<GenericDecryptor>
    {
        public static final String RESOURCE_PATH = "SDE/types/decryptors/GenericDecryptor.yaml";
        private Map<String, GenericDecryptor> cache = (null);

        @Override
        public IMetaCategory<? super GenericDecryptor> category() {
            return Decryptors.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1304;
        }

        @Override
        public String getName() {
            return "GenericDecryptor";
        }

        @Override
        public synchronized Map<String, GenericDecryptor> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(GenericDecryptor.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, GenericDecryptor> types;
        }
    }
}
