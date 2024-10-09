/*
 * Class: ConquistaRepository
 * Description: Repository for the Conquista entity
 * Author: Camilla Ucci de Menezes
 * Creation Date: 01/10/2024
 * Last Updated: 01/10/2024
 */
package bloomera.praceando.praceandoapimg.repository;

import bloomera.praceando.praceandoapimg.model.Conquista;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConquistaRepository extends MongoRepository<Conquista, Long> {
}
