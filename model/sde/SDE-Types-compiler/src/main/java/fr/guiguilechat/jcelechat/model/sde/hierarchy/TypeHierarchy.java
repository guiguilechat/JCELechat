package fr.guiguilechat.jcelechat.model.sde.hierarchy;

import java.util.HashMap;
import java.util.Set;

public class TypeHierarchy {

	public HashMap<Integer, CatDetails> catID2Details = new HashMap<>();
	public HashMap<Integer, Set<Integer>> catID2GroupIDs = new HashMap<>();

	public HashMap<Integer, GroupeDetails> groupID2Details = new HashMap<>();
	public HashMap<Integer, Set<Integer>> groupID2TypeIDs = new HashMap<>();

	public HashMap<Integer, TypeDetails> typeID2Details = new HashMap<>();

	public HashMap<Integer, AttributeDetails> attID2Details = new HashMap<>();

}
