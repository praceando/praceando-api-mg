/*
 * Class: ConquistaRepository
 * Description: Repository for the Conquista entity
 * Author: Camilla Ucci de Menezes
 * Creation Date: 01/10/2024
 * Last Updated: 16/10/2024
 */
package bloomera.praceando.praceandoapimg.repository;

import bloomera.praceando.praceandoapimg.model.Conquista;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ConquistaRepository extends MongoRepository<Conquista, Long> {
    Optional<Conquista> findByIdConquista(Long idConquista);

    List<Conquista> findByIdConquistaInOrderByIdConquista(List<Long> idConquista);
}
