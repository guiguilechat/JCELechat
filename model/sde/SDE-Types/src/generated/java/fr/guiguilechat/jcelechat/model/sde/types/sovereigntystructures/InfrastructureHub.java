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
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.PlanetAnchorDistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanGravimetricStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanLadarStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanMagnetometricStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanRadarStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.SignatureRadius;
import fr.guiguilechat.jcelechat.model.sde.types.SovereigntyStructures;
import org.yaml.snakeyaml.Yaml;

public class InfrastructureHub
    extends SovereigntyStructures
{
    /**
     * How many meters from the standard warp-in distance a planet can be anchored from.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(100000)
    public int planetanchordistance;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {ScanRadarStrength.INSTANCE, PlanetAnchorDistance.INSTANCE, ScanLadarStrength.INSTANCE, Radius.INSTANCE, ScanMagnetometricStrength.INSTANCE, ScanGravimetricStrength.INSTANCE, Capacity.INSTANCE, SignatureRadius.INSTANCE })));
    public static final InfrastructureHub.MetaGroup METAGROUP = new InfrastructureHub.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  865 :
            {
                return planetanchordistance;
            }
            default:
            {
                return super.valueSet((attribute));
            }
        }
    }

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<InfrastructureHub> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<InfrastructureHub>
    {
        public static final String RESOURCE_PATH = "SDE/types/sovereigntystructures/InfrastructureHub.yaml";
        private Map<String, InfrastructureHub> cache = (null);

        @Override
        public IMetaCategory<? super InfrastructureHub> category() {
            return SovereigntyStructures.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1012;
        }

        @Override
        public String getName() {
            return "InfrastructureHub";
        }

        @Override
        public synchronized Map<String, InfrastructureHub> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(InfrastructureHub.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, InfrastructureHub> types;
        }
    }
}
