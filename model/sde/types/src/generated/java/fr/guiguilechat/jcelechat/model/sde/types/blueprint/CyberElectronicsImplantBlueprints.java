package fr.guiguilechat.jcelechat.model.sde.types.blueprint;

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
import fr.guiguilechat.jcelechat.model.sde.types.Blueprint;

public class CyberElectronicsImplantBlueprints
    extends Blueprint
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final CyberElectronicsImplantBlueprints.MetaGroup METAGROUP = new CyberElectronicsImplantBlueprints.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<CyberElectronicsImplantBlueprints> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<CyberElectronicsImplantBlueprints>
    {
        public static final String RESOURCE_PATH = "SDE/types/blueprint/CyberElectronicsImplantBlueprints.yaml";
        private Map<Integer, CyberElectronicsImplantBlueprints> cache = (null);

        @Override
        public IMetaCategory<? super CyberElectronicsImplantBlueprints> category() {
            return Blueprint.METACAT;
        }

        @Override
        public int getGroupId() {
            return  2023;
        }

        @Override
        public String getName() {
            return "CyberElectronicsImplantBlueprints";
        }

        @Override
        public synchronized Map<Integer, CyberElectronicsImplantBlueprints> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(CyberElectronicsImplantBlueprints.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, CyberElectronicsImplantBlueprints> types;
        }
    }
}
