package fr.guiguilechat.jcelechat.libs.spring.connect.services;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.connect.repositories.UserConnectionRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserConnectionService {

	private final UserConnectionRepository repo;

}
