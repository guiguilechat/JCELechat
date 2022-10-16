package fr.guiguilechat.jcelechat.model.sde.types.material;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Material;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class MolecularForgedMaterials
    extends Material
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final MolecularForgedMaterials.MetaGroup METAGROUP = new MolecularForgedMaterials.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<MolecularForgedMaterials> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MolecularForgedMaterials>
    {
        public static final String RESOURCE_PATH = "SDE/types/material/MolecularForgedMaterials.yaml";
        private Map<String, MolecularForgedMaterials> cache = (null);

        @Override
        public IMetaCategory<? super MolecularForgedMaterials> category() {
            return Material.METACAT;
        }

        @Override
        public int getGroupId() {
            return  4096;
        }

        @Override
        public String getName() {
            return "MolecularForgedMaterials";
        }

        @Override
        public synchronized Map<String, MolecularForgedMaterials> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(MolecularForgedMaterials.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<String, MolecularForgedMaterials> types;
        }
    }
}
