package fr.guiguilechat.jcelechat.libs.sde.cache.jsonl;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@RequiredArgsConstructor
public class JsonlKV<K, V> {

	@JsonProperty("_key")
	public K key;

	/// TODO
	///
	/// JsonUnwrapped does not work with generics. anyhow the whole jsonlKV can't
	/// work since internal fields of class `Map<K, V>` should also be changed to
	/// `List<JsonlKV<K, V>>` and this would break inheritance.
	///
	/// I guess only solution is to have dynamic type generation but that really
	/// sucks for so little rewards.
	@JsonUnwrapped
	public final V value;

	@JsonAnySetter
	public void set(String name, Object value) {
		// System.out.println("set " + name + " to " + value + " class " +
		// value.getClass());
	}
}
