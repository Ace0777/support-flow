package br.com.ace.authserviceapi.repository;

import br.com.ace.authserviceapi.models.RefreshToken;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RefreshTokenRepository extends MongoRepository<RefreshToken, String> {
}
