package fr.guiguilechat.jcelechat.model.sde.types.module;

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
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultRealValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.AgilityMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipGroup01;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipGroup02;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipGroup03;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipGroup04;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipGroup05;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipType1;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipType2;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipType3;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipType4;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipType5;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipType6;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipType7;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipType8;
import fr.guiguilechat.jcelechat.model.sde.attributes.CapacitorCapacityMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.CloakingTargetingDelay;
import fr.guiguilechat.jcelechat.model.sde.attributes.CovertCloakCPUAdd;
import fr.guiguilechat.jcelechat.model.sde.attributes.Cpu;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxVelocityModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaGroupID;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevelOld;
import fr.guiguilechat.jcelechat.model.sde.attributes.ModuleReactivationDelay;
import fr.guiguilechat.jcelechat.model.sde.attributes.Power;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanResolutionMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldCapacityMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.SpeedFactor;
import fr.guiguilechat.jcelechat.model.sde.attributes.StabilizeCloakDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.types.Module;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class CloakingDevice
    extends Module
{
    /**
     * Multiplier to the agility of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double agilitymultiplier;
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
    @DefaultIntValue(0)
    public int canfitshipgroup04;
    /**
     * Can be fitted to
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshipgroup05;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshiptype1;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshiptype2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshiptype3;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshiptype4;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshiptype5;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshiptype6;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshiptype7;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshiptype8;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double capacitorcapacitymultiplier;
    /**
     * The time targeting systems take to recalibrate after cloaking.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int cloakingtargetingdelay;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int covertcloakcpuadd;
    /**
     * CPU need of module
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double cpu;
    /**
     * Autogenerated skill attribute, mMaxVelocityBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double maxvelocitymodifier;
    /**
     * Authoring has been moved to FSD.
     * meta group of type
     * 
     *  3: Story-line (Cosmos)
     *  4: Faction
     *  5: Officer (rare asteroid NPCs)
     *  6: Deadspace
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int metagroupid;
    /**
     * Amount of time that has to be waited after the deactivation of this module until it can be reactivated.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int modulereactivationdelay;
    /**
     * current power need
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int power;
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
     * Improves the targeting time of ships by boosting the Scan Resolution.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double scanresolutionmultiplier;
    /**
     * Multiplier to the capacity of a shield.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double shieldcapacitymultiplier;
    /**
     * Factor by which topspeed increases.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double speedfactor;
    /**
     * Duration of the cloak-stabilization dbuff provided by a cloak module
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int stabilizecloakduration;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {Hp.INSTANCE, CovertCloakCPUAdd.INSTANCE, CanFitShipGroup05 .INSTANCE, ShieldCapacityMultiplier.INSTANCE, CanFitShipGroup01 .INSTANCE, CapacitorCapacityMultiplier.INSTANCE, CanFitShipGroup02 .INSTANCE, SpeedFactor.INSTANCE, CanFitShipGroup03 .INSTANCE, RequiredSkill1Level.INSTANCE, CanFitShipGroup04 .INSTANCE, CanFitShipType1 .INSTANCE, CanFitShipType2 .INSTANCE, CanFitShipType3 .INSTANCE, CanFitShipType5 .INSTANCE, CanFitShipType4 .INSTANCE, MetaGroupID.INSTANCE, ModuleReactivationDelay.INSTANCE, Power.INSTANCE, CanFitShipType7 .INSTANCE, Radius.INSTANCE, TechLevel.INSTANCE, Capacity.INSTANCE, AgilityMultiplier.INSTANCE, StabilizeCloakDuration.INSTANCE, CloakingTargetingDelay.INSTANCE, MaxVelocityModifier.INSTANCE, Cpu.INSTANCE, ScanResolutionMultiplier.INSTANCE, RequiredSkill1 .INSTANCE, CanFitShipType8 .INSTANCE, CanFitShipType6 .INSTANCE, MetaLevelOld.INSTANCE })));
    public static final CloakingDevice.MetaGroup METAGROUP = new CloakingDevice.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  169 :
            {
                return agilitymultiplier;
            }
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
            case  1301 :
            {
                return canfitshipgroup04;
            }
            case  1872 :
            {
                return canfitshipgroup05;
            }
            case  1302 :
            {
                return canfitshiptype1;
            }
            case  1303 :
            {
                return canfitshiptype2;
            }
            case  1304 :
            {
                return canfitshiptype3;
            }
            case  1305 :
            {
                return canfitshiptype4;
            }
            case  1944 :
            {
                return canfitshiptype5;
            }
            case  2103 :
            {
                return canfitshiptype6;
            }
            case  2463 :
            {
                return canfitshiptype7;
            }
            case  2486 :
            {
                return canfitshiptype8;
            }
            case  147 :
            {
                return capacitorcapacitymultiplier;
            }
            case  560 :
            {
                return cloakingtargetingdelay;
            }
            case  1870 :
            {
                return covertcloakcpuadd;
            }
            case  50 :
            {
                return cpu;
            }
            case  306 :
            {
                return maxvelocitymodifier;
            }
            case  1692 :
            {
                return metagroupid;
            }
            case  669 :
            {
                return modulereactivationdelay;
            }
            case  30 :
            {
                return power;
            }
            case  182 :
            {
                return requiredskill1;
            }
            case  277 :
            {
                return requiredskill1level;
            }
            case  565 :
            {
                return scanresolutionmultiplier;
            }
            case  146 :
            {
                return shieldcapacitymultiplier;
            }
            case  20 :
            {
                return speedfactor;
            }
            case  3118 :
            {
                return stabilizecloakduration;
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
    public IMetaGroup<CloakingDevice> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<CloakingDevice>
    {
        public static final String RESOURCE_PATH = "SDE/types/module/CloakingDevice.yaml";
        private Map<Integer, CloakingDevice> cache = (null);

        @Override
        public IMetaCategory<? super CloakingDevice> category() {
            return Module.METACAT;
        }

        @Override
        public int getGroupId() {
            return  330;
        }

        @Override
        public String getName() {
            return "CloakingDevice";
        }

        @Override
        public synchronized Map<Integer, CloakingDevice> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(CloakingDevice.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, CloakingDevice> types;
        }
    }
}
