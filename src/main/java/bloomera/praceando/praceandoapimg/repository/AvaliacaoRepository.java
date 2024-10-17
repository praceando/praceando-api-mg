/*
 * Class: AvaliacaoRepository
 * Description: Repository for the Avaliacao entity
 * Author: Camilla Ucci de Menezes
 * Creation Date: 01/10/2024
 * Last Updated: 16/10/2024
 */
package bloomera.praceando.praceandoapimg.repository;

import bloomera.praceando.praceandoapimg.model.Avaliacao;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AvaliacaoRepository extends MongoRepository<Avaliacao, Long> {
    Optional<Avaliacao> findByIdAvaliacao(Long idAvaliacao);
    List<Avaliacao> findAvaliacaosByCdEventoOrderByIdAvaliacao(Long cdEvento);
    Optional<Avaliacao> findFirstByCdEventoAndCdUsuario(Long cdEvento, Long cdUsuario);
}
