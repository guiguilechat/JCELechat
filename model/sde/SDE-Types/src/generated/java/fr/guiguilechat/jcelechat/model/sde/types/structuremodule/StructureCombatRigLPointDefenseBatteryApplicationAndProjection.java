package fr.guiguilechat.jcelechat.model.sde.types.structuremodule;

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
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipGroup01;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipGroup02;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipGroup03;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.HiSecModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.LowSecModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.Mass;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxGroupFitted;
import fr.guiguilechat.jcelechat.model.sde.attributes.NullSecModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.RigSize;
import fr.guiguilechat.jcelechat.model.sde.attributes.SecurityModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureItemVisualFlag;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureRigPDCapUseBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureRigPDRangeBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.UpgradeCost;
import fr.guiguilechat.jcelechat.model.sde.types.StructureModule;
import org.yaml.snakeyaml.Yaml;

public class StructureCombatRigLPointDefenseBatteryApplicationAndProjection
    extends StructureModule
{
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshipgroup01;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshipgroup02;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshipgroup03;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double hisecmodifier;
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double hp;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double lowsecmodifier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxgroupfitted;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double nullsecmodifier;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill1;
    /**
     * Required skill level for skill 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill1level;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rigsize;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int securitymodifier;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int structurerigpdcapusebonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int structurerigpdrangebonus;
    /**
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int techlevel;
    /**
     * How much of the upgrade capacity is used when this is fitted to a ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int upgradecost;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {UpgradeCost.INSTANCE, Radius.INSTANCE, StructureRigPDRangeBonus.INSTANCE, Mass.INSTANCE, StructureRigPDCapUseBonus.INSTANCE, TechLevel.INSTANCE, Capacity.INSTANCE, MaxGroupFitted.INSTANCE, Hp.INSTANCE, RigSize.INSTANCE, CanFitShipGroup01 .INSTANCE, HiSecModifier.INSTANCE, CanFitShipGroup02 .INSTANCE, CanFitShipGroup03 .INSTANCE, LowSecModifier.INSTANCE, RequiredSkill1Level.INSTANCE, NullSecModifier.INSTANCE, SecurityModifier.INSTANCE, RequiredSkill1 .INSTANCE, StructureItemVisualFlag.INSTANCE })));
    public static final StructureCombatRigLPointDefenseBatteryApplicationAndProjection.MetaGroup METAGROUP = new StructureCombatRigLPointDefenseBatteryApplicationAndProjection.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  1298 :
            {
                return canfitshipgroup01;
            }
            case  1299 :
            {
                return canfitshipgroup02;
            }
            case  1300 :
            {
                return canfitshipgroup03;
            }
            case  2355 :
            {
                return hisecmodifier;
            }
            case  9 :
            {
                return hp;
            }
            case  2356 :
            {
                return lowsecmodifier;
            }
            case  1544 :
            {
                return maxgroupfitted;
            }
            case  2357 :
            {
                return nullsecmodifier;
            }
            case  182 :
            {
                return requiredskill1;
            }
            case  277 :
            {
                return requiredskill1level;
            }
            case  1547 :
            {
                return rigsize;
            }
            case  2358 :
            {
                return securitymodifier;
            }
            case  2437 :
            {
                return structurerigpdcapusebonus;
            }
            case  2436 :
            {
                return structurerigpdrangebonus;
            }
            case  422 :
            {
                return techlevel;
            }
            case  1153 :
            {
                return upgradecost;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<StructureCombatRigLPointDefenseBatteryApplicationAndProjection> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<StructureCombatRigLPointDefenseBatteryApplicationAndProjection>
    {
        public static final String RESOURCE_PATH = "SDE/types/structuremodule/StructureCombatRigLPointDefenseBatteryApplicationAndProjection.yaml";
        private Map<String, StructureCombatRigLPointDefenseBatteryApplicationAndProjection> cache = (null);

        @Override
        public IMetaCategory<? super StructureCombatRigLPointDefenseBatteryApplicationAndProjection> category() {
            return StructureModule.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1634;
        }

        @Override
        public String getName() {
            return "StructureCombatRigLPointDefenseBatteryApplicationAndProjection";
        }

        @Override
        public synchronized Map<String, StructureCombatRigLPointDefenseBatteryApplicationAndProjection> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(StructureCombatRigLPointDefenseBatteryApplicationAndProjection.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, StructureCombatRigLPointDefenseBatteryApplicationAndProjection> types;
        }
    }
}