package fr.guiguilechat.jcelechat.model.sde.types.subsystem;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultRealValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.*;
import fr.guiguilechat.jcelechat.model.sde.types.Subsystem;

public class OffensiveSubsystem
    extends Subsystem
{
    /**
     * Capacitor capacity
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double capacitorcapacity;
    /**
     * CPU output of ship
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int cpuoutput;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int dronebandwidth;
    /**
     * This defines the total capacity of drones allowed in the drone bay of the ship
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int dronecapacity;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int launcherhardpointmodifier;
    /**
     * Additional amount of locked targets that can be handled.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxlockedtargetsbonus;
    /**
     * power output of power core
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double poweroutput;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int remotearmorrepairerfalloffbonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int remotearmorrepaireroptimalbonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int remoteshieldboosterfalloffbonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rolebonuscommandburstaoerange;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int subsystembonusamarroffensive;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int subsystembonusamarroffensive2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double subsystembonusamarroffensive3;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double subsystembonuscaldarioffensive;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int subsystembonuscaldarioffensive2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int subsystembonuscaldarioffensive3;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double subsystembonusgallenteoffensive;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int subsystembonusgallenteoffensive2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double subsystembonusgallenteoffensive3;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double subsystembonusminmataroffensive;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double subsystembonusminmataroffensive2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double subsystembonusminmataroffensive3;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int subsystemcommandburstfittingreduction;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int subsystemmetfittingreduction;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int subsystemmhtfittingreduction;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int subsystemmmissilefittingreduction;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int subsystemmptfittingreduction;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int subsystemmrarfittingreduction;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int subsystemmrsbfittingreduction;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int turrethardpointmodifier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int warfarelinkcpupenalty;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {SubsystemCommandBurstFittingReduction.INSTANCE, RemoteShieldBoosterFalloffBonus.INSTANCE, RemoteArmorRepairerFalloffBonus.INSTANCE, RemoteArmorRepairerOptimalBonus.INSTANCE, Hp.INSTANCE, PowerOutput.INSTANCE, RoleBonusCommandBurstAoERange.INSTANCE, RequiredSkill1Level.INSTANCE, SubSystemSlot.INSTANCE, TurretHardPointModifier.INSTANCE, LauncherHardPointModifier.INSTANCE, SubsystemBonusAmarrOffensive.INSTANCE, DroneCapacity.INSTANCE, WarfareLinkCPUPenalty.INSTANCE, HiSlotModifier.INSTANCE, SubsystemBonusGallenteOffensive.INSTANCE, MedSlotModifier.INSTANCE, LowSlotModifier.INSTANCE, CapacitorCapacity.INSTANCE, SubsystemBonusCaldariOffensive.INSTANCE, FitsToShipType.INSTANCE, SubsystemBonusCaldariOffensive2 .INSTANCE, TechLevel.INSTANCE, SubsystemBonusAmarrOffensive2 .INSTANCE, SubsystemBonusMinmatarOffensive.INSTANCE, SubsystemMHTFittingReduction.INSTANCE, MaxLockedTargetsBonus.INSTANCE, SubsystemMPTFittingReduction.INSTANCE, SubsystemMETFittingReduction.INSTANCE, SubsystemMMissileFittingReduction.INSTANCE, SubsystemMRSBFittingReduction.INSTANCE, SubsystemMRARFittingReduction.INSTANCE, CpuOutput.INSTANCE, SubsystemBonusGallenteOffensive2 .INSTANCE, SubsystemBonusMinmatarOffensive2 .INSTANCE, RequiredSkill1 .INSTANCE, DroneBandwidth.INSTANCE, MetaLevelOld.INSTANCE, SubsystemBonusAmarrOffensive3 .INSTANCE, SubsystemBonusGallenteOffensive3 .INSTANCE, SubsystemBonusCaldariOffensive3 .INSTANCE, SubsystemBonusMinmatarOffensive3 .INSTANCE })));
    public static final OffensiveSubsystem.MetaGroup METAGROUP = new OffensiveSubsystem.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  482 :
            {
                return capacitorcapacity;
            }
            case  48 :
            {
                return cpuoutput;
            }
            case  1271 :
            {
                return dronebandwidth;
            }
            case  283 :
            {
                return dronecapacity;
            }
            case  1369 :
            {
                return launcherhardpointmodifier;
            }
            case  235 :
            {
                return maxlockedtargetsbonus;
            }
            case  11 :
            {
                return poweroutput;
            }
            case  2694 :
            {
                return remotearmorrepairerfalloffbonus;
            }
            case  2695 :
            {
                return remotearmorrepaireroptimalbonus;
            }
            case  2693 :
            {
                return remoteshieldboosterfalloffbonus;
            }
            case  2574 :
            {
                return rolebonuscommandburstaoerange;
            }
            case  1434 :
            {
                return subsystembonusamarroffensive;
            }
            case  1511 :
            {
                return subsystembonusamarroffensive2;
            }
            case  1531 :
            {
                return subsystembonusamarroffensive3;
            }
            case  1444 :
            {
                return subsystembonuscaldarioffensive;
            }
            case  1510 :
            {
                return subsystembonuscaldarioffensive2;
            }
            case  1533 :
            {
                return subsystembonuscaldarioffensive3;
            }
            case  1439 :
            {
                return subsystembonusgallenteoffensive;
            }
            case  1521 :
            {
                return subsystembonusgallenteoffensive2;
            }
            case  1532 :
            {
                return subsystembonusgallenteoffensive3;
            }
            case  1449 :
            {
                return subsystembonusminmataroffensive;
            }
            case  1522 :
            {
                return subsystembonusminmataroffensive2;
            }
            case  1534 :
            {
                return subsystembonusminmataroffensive3;
            }
            case  2692 :
            {
                return subsystemcommandburstfittingreduction;
            }
            case  2668 :
            {
                return subsystemmetfittingreduction;
            }
            case  2666 :
            {
                return subsystemmhtfittingreduction;
            }
            case  2669 :
            {
                return subsystemmmissilefittingreduction;
            }
            case  2667 :
            {
                return subsystemmptfittingreduction;
            }
            case  2671 :
            {
                return subsystemmrarfittingreduction;
            }
            case  2670 :
            {
                return subsystemmrsbfittingreduction;
            }
            case  1368 :
            {
                return turrethardpointmodifier;
            }
            case  1883 :
            {
                return warfarelinkcpupenalty;
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
    public IMetaGroup<OffensiveSubsystem> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<OffensiveSubsystem>
    {
        public static final String RESOURCE_PATH = "SDE/types/subsystem/OffensiveSubsystem.yaml";
        private Map<Integer, OffensiveSubsystem> cache = (null);

        @Override
        public IMetaCategory<? super OffensiveSubsystem> category() {
            return Subsystem.METACAT;
        }

        @Override
        public int getGroupId() {
            return  956;
        }

        @Override
        public String getName() {
            return "OffensiveSubsystem";
        }

        @Override
        public synchronized Map<Integer, OffensiveSubsystem> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(OffensiveSubsystem.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, OffensiveSubsystem> types;
        }
    }
}
