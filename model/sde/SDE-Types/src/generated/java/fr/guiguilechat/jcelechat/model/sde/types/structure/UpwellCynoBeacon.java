package fr.guiguilechat.jcelechat.model.sde.types.structure;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.types.Structure;
import org.yaml.snakeyaml.Yaml;

public class UpwellCynoBeacon
    extends Structure
{
    /**
     * Module type ID to pre-fit into service slot 0
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int PreFitServiceSlot0;
    /**
     * scanning speed in milliseconds
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int ScanSpeed;
    /**
     * DO NOT MESS WITH
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double StructureUniformity;
    public static final UpwellCynoBeacon.MetaGroup METAGROUP = new UpwellCynoBeacon.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  2792 :
            {
                return PreFitServiceSlot0;
            }
            case  79 :
            {
                return ScanSpeed;
            }
            case  525 :
            {
                return StructureUniformity;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<UpwellCynoBeacon> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<UpwellCynoBeacon>
    {
        public static final String RESOURCE_PATH = "SDE/types/structure/UpwellCynoBeacon.yaml";
        private Map<String, UpwellCynoBeacon> cache = (null);

        @Override
        public IMetaCategory<? super UpwellCynoBeacon> category() {
            return Structure.METACAT;
        }

        @Override
        public int getGroupId() {
            return  2017;
        }

        @Override
        public String getName() {
            return "UpwellCynoBeacon";
        }

        @Override
        public synchronized Map<String, UpwellCynoBeacon> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(UpwellCynoBeacon.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, UpwellCynoBeacon> types;
        }
    }
}
