/*
 * Class: ConquistaUserService
 * Description: Service for the ConquistaUser entity.
 * Author: Camilla Ucci de Menezes
 * Creation Date: 09/10/2024
 * Last Updated: 13/10/2024
 */
package bloomera.praceando.praceandoapimg.service;

import bloomera.praceando.praceandoapimg.model.ConquistaUser;
import bloomera.praceando.praceandoapimg.repository.ConquistaUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConquistaUserService {

    private final ConquistaUserRepository conquistaUserRepository;

    public ConquistaUserService(ConquistaUserRepository conquistaUserRepository) {
        this.conquistaUserRepository = conquistaUserRepository;
    }

    /**
     * @return uma lista de conquistas de usuários se existirem, ou null se não houver nenhuma.
     */
    public List<ConquistaUser> getConquistaUsers() {
        List<ConquistaUser> conquistaUsers = conquistaUserRepository.findAll();
        return conquistaUsers.isEmpty() ? null : conquistaUsers;
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
