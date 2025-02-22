package fr.guiguilechat.jcelechat.libs.spring.connect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * can add specified additional authorities onto a delegate {@link OAuth2User}
 */
@RequiredArgsConstructor
public class DelegateOauth2User implements OAuth2User {

	private final OAuth2User delegate;

	private final List<String> addRoles;

	@Override
	public Map<String, Object> getAttributes() {
		return delegate.getAttributes();
	}

	@Getter(lazy = true)
	private final Collection<? extends GrantedAuthority> authorities = makeAuthorities();

	protected Collection<? extends GrantedAuthority> makeAuthorities() {
		List<GrantedAuthority> ret = new ArrayList<>(delegate.getAuthorities());
		if (addRoles != null) {
			for (String role : addRoles) {
				ret.add(new SimpleGrantedAuthority(role));
			}
		}
		return ret;
	}

	@Override
	public String getName() {
		return delegate.getName();
	}

}
