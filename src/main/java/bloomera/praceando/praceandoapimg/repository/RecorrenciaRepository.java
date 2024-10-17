/*
 * Class: RecorrenciaRepository
 * Description: Repository for the Recorrencia entity
 * Author: Camilla Ucci de Menezes
 * Creation Date: 01/10/2024
 * Last Updated: 16/10/2024
 */
package bloomera.praceando.praceandoapimg.repository;

import bloomera.praceando.praceandoapimg.model.Recorrencia;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RecorrenciaRepository extends MongoRepository<Recorrencia, Long> {
    Optional<Recorrencia> findByIdRecorrencia(Long idRecorrencia);
    Optional<Recorrencia> findByIdEvento(Long idEvento);
}
