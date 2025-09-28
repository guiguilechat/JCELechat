package fr.guiguilechat.jcelechat.libs.sde.cache;

import java.lang.ref.WeakReference;
import java.util.Objects;

public interface SDECacheListener {

	void onSDECacheCleared();

	public static class Ref extends WeakReference<SDECacheListener> {

		public Ref(SDECacheListener referent) {
			super(referent);
		}

		@Override
		public int hashCode() {
			var v = get();
			return v == null ? 0 : v.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}
			if (obj == this) {
				return true;
			}
			if (obj instanceof Ref r) {
				return Objects.equals(get(), r.get());
			}
			return false;
		}

		public void clearRefCache() {
			var v = get();
			if (v != null) {
				v.onSDECacheCleared();
			}
		}

	}

}
