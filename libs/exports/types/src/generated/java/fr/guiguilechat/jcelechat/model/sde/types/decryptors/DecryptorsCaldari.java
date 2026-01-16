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
import fr.guiguilechat.jcelechat.model.sde.attributes.InventionMEModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.InventionMaxRunModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.InventionPropabilityMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.InventionTEModifier;
import fr.guiguilechat.jcelechat.model.sde.types.Decryptors;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class DecryptorsCaldari
    extends Decryptors
{
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {InventionMaxRunModifier.INSTANCE, InventionPropabilityMultiplier.INSTANCE, InventionMEModifier.INSTANCE, InventionTEModifier.INSTANCE })));
    public static final DecryptorsCaldari.MetaGroup METAGROUP = new DecryptorsCaldari.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<DecryptorsCaldari> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DecryptorsCaldari>
    {
        public static final String RESOURCE_PATH = "SDE/types/decryptors/DecryptorsCaldari.yaml";
        private Map<Integer, DecryptorsCaldari> cache = (null);

        @Override
        public IMetaCategory<? super DecryptorsCaldari> category() {
            return Decryptors.METACAT;
        }

        @Override
        public int getGroupId() {
            return  731;
        }

        @Override
        public String getName() {
            return "DecryptorsCaldari";
        }

        @Override
        public synchronized Map<Integer, DecryptorsCaldari> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(DecryptorsCaldari.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, DecryptorsCaldari> types;
        }
    }
}
