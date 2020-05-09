package fr.guiguilechat.jcelechat.model.sde.types.sovereigntystructures;

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
import fr.guiguilechat.jcelechat.model.sde.attributes.Mass;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanGravimetricStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanLadarStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanMagnetometricStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanRadarStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.SignatureRadius;
import fr.guiguilechat.jcelechat.model.sde.types.SovereigntyStructures;
import org.yaml.snakeyaml.Yaml;

public class TerritorialClaimUnit
    extends SovereigntyStructures
{
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {ScanRadarStrength.INSTANCE, ScanLadarStrength.INSTANCE, Radius.INSTANCE, ScanMagnetometricStrength.INSTANCE, ScanGravimetricStrength.INSTANCE, Mass.INSTANCE, Capacity.INSTANCE, SignatureRadius.INSTANCE })));
    public static final TerritorialClaimUnit.MetaGroup METAGROUP = new TerritorialClaimUnit.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<TerritorialClaimUnit> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<TerritorialClaimUnit>
    {
        public static final String RESOURCE_PATH = "SDE/types/sovereigntystructures/TerritorialClaimUnit.yaml";
        private Map<String, TerritorialClaimUnit> cache = (null);

        @Override
        public IMetaCategory<? super TerritorialClaimUnit> category() {
            return SovereigntyStructures.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1003;
        }

        @Override
        public String getName() {
            return "TerritorialClaimUnit";
        }

        @Override
        public synchronized Map<String, TerritorialClaimUnit> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(TerritorialClaimUnit.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, TerritorialClaimUnit> types;
        }
    }
}
