/*
 * Class: AvaliacaoRepository
 * Description: Repository for the Avaliacao entity
 * Author: Camilla Ucci de Menezes
 * Creation Date: 01/10/2024
 * Last Updated: 01/10/2024
 */
package bloomera.praceando.praceandoapimg.repository;

import bloomera.praceando.praceandoapimg.model.Avaliacao;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AvaliacaoRepository extends MongoRepository<Avaliacao, Long> {
}
