/*
 * Class: ConquistaService
 * Description: Service for the Conquista entity.
 * Author: Camilla Ucci de Menezes
 * Creation Date: 09/10/2024
 * Last Updated: 09/10/2024
 */
package bloomera.praceando.praceandoapimg.service;

import bloomera.praceando.praceandoapimg.model.Conquista;
import bloomera.praceando.praceandoapimg.repository.ConquistaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConquistaService {

    private final ConquistaRepository conquistaRepository;

    public ConquistaService(ConquistaRepository conquistaRepository) {
        this.conquistaRepository = conquistaRepository;
    }

    /**
     * @return uma lista de conquistas se existirem, ou null se não houver nenhuma conquista.
     */
    public List<Conquista> getConquistas() {
        List<Conquista> conquistas = conquistaRepository.findAll();
        return conquistas.isEmpty() ? null : conquistas;
    }

    /**
     * @return conquista pelo id, se ela existir, caso contrário, retorna null.
     */
    public Conquista getConquistaById(Long id) {
        Optional<Conquista> conquista = conquistaRepository.findById(id);
        return conquista.orElse(null);
    }

    /**
     * @return conquista deletada.
     */
    public Conquista deleteConquistaById(Long id) {
        Conquista conquista = getConquistaById(id);
        if (conquista != null) conquistaRepository.deleteById(id);
        return conquista;
    }

    /**
     * @return conquista inserida.
     */
    public Conquista saveConquista(Conquista conquista) {
        return conquistaRepository.save(conquista);
    }

    /**
     * Atualiza os dados de uma conquista.
     * @param id Id da conquista a ser atualizada.
     * @param conquista Conquista com os novos dados.
     * @return conquista atualizada ou nulo, caso a conquista não exista.
     */
    public Conquista updateConquista(Long id, Conquista conquista) {
        Conquista existingConquista = getConquistaById(id);
        if (existingConquista != null) {
            existingConquista.setIdConquista(conquista.getIdConquista());
            existingConquista.setNmConquista(conquista.getNmConquista());
            existingConquista.setDsConquista(conquista.getDsConquista());
            existingConquista.setNmTipo(conquista.getNmTipo());
            existingConquista.setQtPolen(conquista.getQtPolen());
            return conquistaRepository.save(existingConquista);
        } else {
            return null;
        }
    }
}
