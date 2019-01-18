package fr.guiguilechat.jcelechat.model.sde.items.types.structure;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Structure;
import org.yaml.snakeyaml.Yaml;

public class UpwellJumpGate
    extends Structure
{
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int GateMaxJumpMass;
    /**
     * Type that is used for consumption from cargo hold when activating jump drive operation.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int JumpDriveConsumptionType;
    /**
     * Range in light years the ship can maximum jump to.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double JumpDriveRange;
    /**
     * Additional units of fuel that are consumed on each jump through a jump portal, not subject to any of the mass or distance multipliers
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int JumpPortalAdditionalConsumption;
    /**
     * Multiplier used to calculate amount of quantity used for jumping via portals based on mass of ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double JumpPortalConsumptionMassFactor;
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
    public static final UpwellJumpGate.MetaGroup METAGROUP = new UpwellJumpGate.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  2798 :
            {
                return GateMaxJumpMass;
            }
            case  866 :
            {
                return JumpDriveConsumptionType;
            }
            case  867 :
            {
                return JumpDriveRange;
            }
            case  2793 :
            {
                return JumpPortalAdditionalConsumption;
            }
            case  1001 :
            {
                return JumpPortalConsumptionMassFactor;
            }
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
    public IMetaGroup<UpwellJumpGate> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<UpwellJumpGate>
    {
        public static final String RESOURCE_PATH = "SDE/items/structure/UpwellJumpGate.yaml";
        private Map<String, UpwellJumpGate> cache = (null);

        @Override
        public IMetaCategory<? super UpwellJumpGate> category() {
            return Structure.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1408;
        }

        @Override
        public String getName() {
            return "UpwellJumpGate";
        }

        @Override
        public synchronized Map<String, UpwellJumpGate> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(UpwellJumpGate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, UpwellJumpGate> items;
        }
    }
}
