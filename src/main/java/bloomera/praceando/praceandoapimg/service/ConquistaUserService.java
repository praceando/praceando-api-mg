/*
 * Class: ConquistaUserService
 * Description: Service for the ConquistaUser entity.
 * Author: Camilla Ucci de Menezes
 * Creation Date: 09/10/2024
 * Last Updated: 16/10/2024
 */
package bloomera.praceando.praceandoapimg.service;

import bloomera.praceando.praceandoapimg.model.Conquista;
import bloomera.praceando.praceandoapimg.model.ConquistaUser;
import bloomera.praceando.praceandoapimg.repository.ConquistaRepository;
import bloomera.praceando.praceandoapimg.repository.ConquistaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConquistaUserService {

    private final ConquistaUserRepository conquistaUserRepository;
    @Autowired
    private ConquistaRepository conquistaRepository;

    public ConquistaUserService(ConquistaUserRepository conquistaUserRepository) {
        this.conquistaUserRepository = conquistaUserRepository;
    }

    /**
     * @return uma lista de conquistas de usuários se existirem, ou null se não houver nenhuma.
     */
    public List<Conquista> getConquistaUser(Long cdUsuario) {
        List<ConquistaUser> conquistaUsers = conquistaUserRepository.findByCdUsuario(cdUsuario);

        List<Long> idsConquista = conquistaUsers.stream()
                .map(ConquistaUser::getIdConquistaUser)
                .collect(Collectors.toList());

        return conquistaRepository.findByIdConquistaInOrderByIdConquista(idsConquista);
    }

    /**
     * @return conquista do usuário pelo id, se ela existir, caso contrário, retorna null.
     */
    public ConquistaUser getConquistaUserById(Long id) {
        Optional<ConquistaUser> conquistaUser = conquistaUserRepository.findByIdConquistaUser(id);
        return conquistaUser.orElse(null);
    }

    /**
     * @return conquista de usuário deletada.
     */
    public ConquistaUser deleteConquistaUserById(Long id) {
        ConquistaUser conquistaUser = getConquistaUserById(id);
        if (conquistaUser != null) conquistaUserRepository.delete(conquistaUser);
        return conquistaUser;
    }

    /**
     * @return conquista de usuário inserida.
     */
    public ConquistaUser saveConquistaUser(ConquistaUser conquistaUser) {
        return conquistaUserRepository.save(conquistaUser);
    }

    /**
     * Atualiza os dados de uma conquista de usuário.
     * @param id Id da conquista do usuário a ser atualizada.
     * @param conquistaUser ConquistaUser com os novos dados.
     * @return conquista do usuário atualizada ou nulo, caso não exista.
     */
    public ConquistaUser updateConquistaUser(Long id, ConquistaUser conquistaUser) {
        ConquistaUser existingConquistaUser = getConquistaUserById(id);
        if (existingConquistaUser != null) {
            existingConquistaUser.setCdUsuario(conquistaUser.getCdUsuario());
            existingConquistaUser.setCdConquista(conquistaUser.getCdConquista());
            return conquistaUserRepository.save(existingConquistaUser);
        } else {
            return null;
        }
    }
}
