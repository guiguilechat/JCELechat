package fr.guiguilechat.eveonline.database.retrieval.sde.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import fr.guiguilechat.eveonline.database.ESIMarketPrices;
import fr.guiguilechat.eveonline.database.EveCentral;
import fr.guiguilechat.eveonline.sde.bsd.EdgmAttributeTypes;
import fr.guiguilechat.eveonline.sde.bsd.EdgmEffects;
import fr.guiguilechat.eveonline.sde.bsd.EdgmTypeAttributes;
import fr.guiguilechat.eveonline.sde.bsd.EdgmTypeEffects;
import fr.guiguilechat.eveonline.sde.fsd.Eblueprints;
import fr.guiguilechat.eveonline.sde.fsd.Eblueprints.BPActivities.Activity;
import fr.guiguilechat.eveonline.sde.fsd.Eblueprints.Material;
import fr.guiguilechat.eveonline.sde.fsd.EcategoryIDs;
import fr.guiguilechat.eveonline.sde.fsd.EgroupIDs;
import fr.guiguilechat.eveonline.sde.fsd.EtypeIDs;
import fr.guiguilechat.eveonline.sde.model.IndustryUsages;
import fr.guiguilechat.eveonline.sde.model.InventionDecryptor;

/**
 * cache SDE data and evecentral data.
 */
public class SDEData {

	// bsd

	protected LinkedHashMap<Integer, EdgmAttributeTypes> cachedAttributeTypes = null;

	public LinkedHashMap<Integer, EdgmAttributeTypes> getAttributeTypes() {
		if (cachedAttributeTypes == null) {
			cachedAttributeTypes = EdgmAttributeTypes.loadByAttributeID();
		}
		return cachedAttributeTypes;
	}

	protected LinkedHashMap<Integer, EdgmEffects> cachedEffects = null;

	public LinkedHashMap<Integer, EdgmEffects> getEffects() {
		if (cachedEffects == null) {
			cachedEffects = EdgmEffects.loadByEffectID();
		}
		return cachedEffects;
	}

	protected LinkedHashMap<Integer, HashMap<Integer, EdgmTypeAttributes>> cachedTypeAttributes = null;

	public LinkedHashMap<Integer, HashMap<Integer, EdgmTypeAttributes>> getTypeAttributes() {
		if (cachedTypeAttributes == null) {
			cachedTypeAttributes = EdgmTypeAttributes.loadByTypeIDAttributeID();
		}
		return cachedTypeAttributes;
	}

	protected LinkedHashMap<Integer, HashMap<Integer, EdgmTypeEffects>> cachedTypeEffects = null;

	/**
	 * get the cached typeID->effectId->EdgmTypeeffect
	 *
	 * @return
	 */
	public LinkedHashMap<Integer, HashMap<Integer, EdgmTypeEffects>> getTypeEffects() {
		if (cachedTypeEffects == null) {
			cachedTypeEffects = EdgmTypeEffects.loadByTypeIDEffectID();
		}
		return cachedTypeEffects;
	}

	// fsd

	protected LinkedHashMap<Integer, Eblueprints> cachedBlueprints = null;

	public LinkedHashMap<Integer, Eblueprints> getBlueprints() {
		if (cachedBlueprints == null) {
			cachedBlueprints = Eblueprints.load();
		}
		return cachedBlueprints;
	}

	protected LinkedHashMap<Integer, EcategoryIDs> cachedCategoryIDs = null;

	public LinkedHashMap<Integer, EcategoryIDs> getCategoryIDs() {
		if (cachedCategoryIDs == null) {
			cachedCategoryIDs = EcategoryIDs.load();
		}
		return cachedCategoryIDs;
	}

	protected LinkedHashMap<Integer, EgroupIDs> cachedGroupIDs = null;

	public LinkedHashMap<Integer, EgroupIDs> getGroupIDs() {
		if (cachedGroupIDs == null) {
			cachedGroupIDs = EgroupIDs.load();
		}
		return cachedGroupIDs;
	}

	protected LinkedHashMap<Integer, EtypeIDs> cachedTypeIDs = null;

	public LinkedHashMap<Integer, EtypeIDs> getTypeIDs() {
		if (cachedTypeIDs == null) {
			cachedTypeIDs = EtypeIDs.load();
		}
		return cachedTypeIDs;
	}

	public EtypeIDs getType(int id) {
		return getTypeIDs().get(id);
	}

	protected HashMap<String, Integer> cachedDico = null;

	/**
	 * get the dictionary of names (lower case) to the types ids
	 * 
	 * @return the internal cached dictionary
	 */
	public HashMap<String, Integer> getDico() {
		if (cachedDico == null) {
			cachedDico = new HashMap<>();
			for (Entry<Integer, EtypeIDs> e : getTypeIDs().entrySet()) {
				cachedDico.put(e.getValue().enName().toLowerCase(), e.getKey());
			}
		}
		return cachedDico;
	}

	protected ESIMarketPrices esi = new ESIMarketPrices();

	public ESIMarketPrices getESI() {
		return esi;
	}

	// central cache

	protected HashMap<Long, EveCentral> centrals = new HashMap<>();

	/**
	 * get a cached central for given system.
	 *
	 * @param systemID
	 *          ID of system, Jita by default.
	 * @return the internal cached eve-central proxy
	 */
	public EveCentral central(long systemID) {
		EveCentral ret = centrals.get(systemID);
		if (ret == null) {
			ret = new EveCentral(systemID);
			centrals.put(systemID, ret);
		}
		return ret;
	}

	public EveCentral central() {
		return central(EveCentral.JITA_SYSTEM);
	}

	// invetion decryptors

	protected ArrayList<InventionDecryptor> cachedDecryptors = null;

	public ArrayList<InventionDecryptor> getInventionDecryptors() {
		if (cachedDecryptors == null) {
			// group of decryptors. hardcoded since I don't know how to get the
			HashSet<Integer> groups = new HashSet<>();
			groups.add(1304);

			cachedDecryptors = new ArrayList<>();

			int maxRunAttID = 0, probMultAttID = 0, MEAttID = 0, TEAttID = 0;

			for (Entry<Integer, EtypeIDs> e : EtypeIDs.load().entrySet()) {
				if (groups.contains(e.getValue().groupID) && e.getValue().published) {
					int itemId = e.getKey();
					InventionDecryptor dec = new InventionDecryptor();
					cachedDecryptors.add(dec);
					dec.id = itemId;
					dec.name = e.getValue().enName();
					HashMap<Integer, EdgmTypeAttributes> itemAttributes = getTypeAttributes().get(itemId);
					if (itemAttributes != null) {
						for (Entry<Integer, EdgmTypeAttributes> e2 : itemAttributes.entrySet()) {
							int attId = e2.getKey();
							EdgmTypeAttributes att = e2.getValue();
							if (attId != maxRunAttID && attId != probMultAttID && attId != MEAttID && attId != TEAttID) {
								String attName = getAttributeTypes().get(attId).attributeName;
								if (attName.contains("MaxRun")) {
									maxRunAttID = attId;
								} else if (attName.contains("Multiplier")) {
									probMultAttID = attId;
								} else if (attName.contains("MEModifier")) {
									MEAttID = attId;
								} else if (attName.contains("TEModifier")) {
									TEAttID = attId;
								} else {
									System.err.println(
											"can't translate attribute id " + attId + " with name " + attName + " in a decryptor attribute");
								}
							}
							if (attId == maxRunAttID) {
								dec.maxrun = (int) att.valueFloat;
							} else if (attId == probMultAttID) {
								dec.probmult = att.valueFloat;
							} else if (attId == MEAttID) {
								dec.me = (int) att.valueFloat;
							} else if (attId == TEAttID) {
								dec.te = (int) att.valueFloat;
							} else {
								System.err.println("unknown attribute " + attId);
							}
						}
					}
				}
			}
		}
		return cachedDecryptors;
	}

	// all industry usages

	protected HashMap<Integer, IndustryUsages> cachedUsages = null;

	protected IndustryUsages getCreateUsages(int itemID) {
		IndustryUsages ret = cachedUsages.get(itemID);
		if (ret == null) {
			ret = new IndustryUsages();
			cachedUsages.put(itemID, ret);
		}
		return ret;
	}

	public HashMap<Integer, IndustryUsages> getIndustryUsages() {
		if (cachedUsages == null) {
			cachedUsages = new HashMap<>();
			for (Eblueprints bp : getBlueprints().values()) {
				for (Activity act : new Activity[] { 
						bp.activities.copying, 
						bp.activities.invention,
						bp.activities.manufacturing }) {
					for (Material m : act.materials) {
						getCreateUsages(m.typeID).asMaterial.add(bp);
					}
					for (Material m : act.products) {
						getCreateUsages(m.typeID).asProduct.add(bp);
					}
				}
			}
		}
		return cachedUsages;
	}

	/**
	 * get the blueprint that allows to create an item with invention or
	 * manufacturing
	 *
	 * @param itemID
	 * @return the set of blueprint which have the given item id in their products
	 */
	public Set<Eblueprints> getParentBlueprints(int itemID) {
		IndustryUsages usages = getIndustryUsages().get(itemID);
		return usages == null ? Collections.emptySet() : usages.asProduct;
	}

	/**
	 * get the bp that manufacture an item
	 *
	 * @param itemID
	 * @return
	 */
	public Stream<Eblueprints> getParentInvention(int itemID) {
		IndustryUsages usages = getIndustryUsages().get(itemID);
		if (usages == null) {
			return Stream.empty();
		}
		return usages.asProduct.stream()
				.filter(bp -> bp.activities.invention.products.stream().filter(m -> m.typeID == itemID).findAny().isPresent());
	}

	/**
	 * get the bp that invent an item
	 *
	 * @param itemID
	 * @return
	 */
	public Stream<Eblueprints> getParentManufacturing(int itemID) {
		IndustryUsages usages = getIndustryUsages().get(itemID);
		if (usages == null) {
			return Stream.empty();
		}
		return usages.asProduct.stream().filter(
				bp -> bp.activities.manufacturing.products.stream().filter(m -> m.typeID == itemID).findAny().isPresent());
	}

	public Stream<Eblueprints> getParentBPO(int itemID) {
		// TODO
		// getParentManufacturing(itemID).flatMap(getIndustryUsages())
		return null;
	}

	public IntStream getGroupsForCategory(int categoryId) {
		return getGroupIDs().entrySet().stream().filter(e -> e.getValue().categoryID == categoryId).mapToInt(Entry::getKey);
	}

	public IntStream getTypeIDsForGroup(int groupID) {
		return getTypeIDs().entrySet().stream().filter(e -> e.getValue().groupID == groupID).mapToInt(Entry::getKey);
	}

	public IntStream getTypeIDsForCategory(int catID) {
		return getGroupsForCategory(catID).flatMap(this::getTypeIDsForGroup);
	}

}
