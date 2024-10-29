/*
 * Class: CounterRepository
 * Description: Repository for the Counter entity
 * Author: Camilla Ucci de Menezes
 * Creation Date: 29/10/2024
 * Last Updated: 29/10/2024
 */
package bloomera.praceando.praceandoapimg.repository;

import bloomera.praceando.praceandoapimg.model.Avaliacao;
import bloomera.praceando.praceandoapimg.model.Counter;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CounterRepository extends MongoRepository<Counter, Long> {
    Optional<Counter> findById(String idAvaliacao);

}
