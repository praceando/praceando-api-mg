/*
 * Class: RecorrenciaRepository
 * Description: Repository for the Recorrencia entity
 * Author: Camilla Ucci de Menezes
 * Creation Date: 01/10/2024
 * Last Updated: 01/10/2024
 */
package bloomera.praceando.praceandoapimg.repository;

import bloomera.praceando.praceandoapimg.model.Recorrencia;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RecorrenciaRepository extends MongoRepository<Recorrencia, Long> {
}
