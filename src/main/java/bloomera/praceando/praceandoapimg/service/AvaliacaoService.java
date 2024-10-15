/*
 * Class: AvaliacaoService
 * Description: Service for the Avaliacao entity.
 * Author: Camilla Ucci de Menezes
 * Creation Date: 03/10/2024
 * Last Updated: 13/10/2024
 */
package bloomera.praceando.praceandoapimg.service;

import bloomera.praceando.praceandoapimg.model.Avaliacao;
import bloomera.praceando.praceandoapimg.repository.AvaliacaoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;

    public AvaliacaoService(AvaliacaoRepository avaliacaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
    }

    /**
     * @return uma lista de documentos Avaliação se existirem, ou null se não houver nenhuma avaliação.
     */
    public List<Avaliacao> getAvaliacoes() {
        List<Avaliacao> avaliacoes = avaliacaoRepository.findAll();
        return avaliacoes.isEmpty() ? null : avaliacoes;
    }

    /**
     * @return avaliação pelo id, se ela existir, caso contrário, retorna null
     */
    public Avaliacao getAvaliacaoById(Long id) {
        Optional<Avaliacao> avaliacao = avaliacaoRepository.findByIdAvaliacao(id);
        return avaliacao.orElse(null);
    }

    /**
     * @return avaliacao deletada.
     */
    public Avaliacao deleteAvaliacaoById(Long id) {
        Avaliacao avaliacao = getAvaliacaoById(id);
        if (avaliacao != null) avaliacaoRepository.delete(avaliacao);
        return avaliacao;
    }

    /**
     * @return avaliação inserida.
     */
    public Avaliacao saveAvaliacao(Avaliacao avaliacao) {
        avaliacao.setDtAtualizacao(LocalDateTime.now());
        return avaliacaoRepository.save(avaliacao);
    }

    /**
     * Atualiza os dados de uma avaliação.
     * @param id Id da avaliação a ser atualizada.
     * @param avaliacao Avaliação com os novos dados.
     * @return avaliação atualizada ou nulo, caso a avaliação não exista
     */
    public Avaliacao updateAvaliacao(Long id, Avaliacao avaliacao) {
        Avaliacao existingAvaliacao = getAvaliacaoById(id);
        if (existingAvaliacao != null) {
            existingAvaliacao.setCdEvento(avaliacao.getCdEvento());
            existingAvaliacao.setCdUsuario(avaliacao.getCdUsuario());
            existingAvaliacao.setNrNota(avaliacao.getNrNota());
            existingAvaliacao.setDsComentario(avaliacao.getDsComentario());
            existingAvaliacao.setNrReacao(avaliacao.getNrReacao());
            existingAvaliacao.setDtAtualizacao(avaliacao.getDtAtualizacao());
            return avaliacaoRepository.save(existingAvaliacao);
        } else {
            return null;
        }
    }
}
