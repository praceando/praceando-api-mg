/*
 * Class: RecorrenciaService
 * Description: Service for the Recorrencia entity.
 * Author: Camilla Ucci de Menezes
 * Creation Date: 09/10/2024
 * Last Updated: 09/10/2024
 */
package bloomera.praceando.praceandoapimg.service;

import bloomera.praceando.praceandoapimg.model.Recorrencia;
import bloomera.praceando.praceandoapimg.repository.RecorrenciaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecorrenciaService {

    private final RecorrenciaRepository recorrenciaRepository;

    public RecorrenciaService(RecorrenciaRepository recorrenciaRepository) {
        this.recorrenciaRepository = recorrenciaRepository;
    }

    /**
     * @return uma lista de recorrências se existirem, ou null se não houver nenhuma.
     */
    public List<Recorrencia> getRecorrencias() {
        List<Recorrencia> recorrencias = recorrenciaRepository.findAll();
        return recorrencias.isEmpty() ? null : recorrencias;
    }

    /**
     * @return recorrência pelo id, se ela existir, caso contrário, retorna null.
     */
    public Recorrencia getRecorrenciaById(Long id) {
        Optional<Recorrencia> recorrencia = recorrenciaRepository.findById(id);
        return recorrencia.orElse(null);
    }

    /**
     * @return recorrência deletada.
     */
    public Recorrencia deleteRecorrenciaById(Long id) {
        Recorrencia recorrencia = getRecorrenciaById(id);
        if (recorrencia != null) recorrenciaRepository.deleteById(id);
        return recorrencia;
    }

    /**
     * @return recorrência inserida.
     */
    public Recorrencia saveRecorrencia(Recorrencia recorrencia) {
        return recorrenciaRepository.save(recorrencia);
    }

    /**
     * Atualiza os dados de uma recorrência.
     * @param id Id da recorrência a ser atualizada.
     * @param recorrencia Recorrencia com os novos dados.
     * @return recorrência atualizada ou nulo, caso não exista.
     */
    public Recorrencia updateRecorrencia(Long id, Recorrencia recorrencia) {
        Recorrencia existingRecorrencia = getRecorrenciaById(id);
        if (existingRecorrencia != null) {
            existingRecorrencia.setIdRecorrencia(recorrencia.getIdRecorrencia());
            existingRecorrencia.setDsTipo(recorrencia.getDsTipo());
            existingRecorrencia.setDsDiasDaSemana(recorrencia.getDsDiasDaSemana());
            existingRecorrencia.setCdEvento(recorrencia.getCdEvento());
            return recorrenciaRepository.save(existingRecorrencia);
        } else {
            return null;
        }
    }
}

