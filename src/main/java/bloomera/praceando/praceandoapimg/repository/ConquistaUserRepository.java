/*
 * Class: ConquistaUserRepository
 * Description: Repository for the ConquistaUser entity
 * Author: Camilla Ucci de Menezes
 * Creation Date: 01/10/2024
 * Last Updated: 13/10/2024
 */
package bloomera.praceando.praceandoapimg.repository;

import bloomera.praceando.praceandoapimg.model.ConquistaUser;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ConquistaUserRepository extends MongoRepository<ConquistaUser, Long> {
    Optional<ConquistaUser> findByIdConquistaUser(Long idConquistaUser);
}
