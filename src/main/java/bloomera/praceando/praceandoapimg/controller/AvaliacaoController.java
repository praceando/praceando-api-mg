/*
 * Class: AvaliacaoController
 * Description: Controller for the Avaliacao entity.
 * Author: Camilla Ucci de Menezes
 * Creation Date: 07/10/2024
 * Last Updated: 07/10/2024
 */
package bloomera.praceando.praceandoapimg.controller;

import bloomera.praceando.praceandoapimg.model.Avaliacao;
import bloomera.praceando.praceandoapimg.service.AvaliacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/avaliacao")
@Tag(name = "Avaliacao", description = "Gerenciar avaliações de eventos")
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;

    @Autowired
    public AvaliacaoController(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    @GetMapping("/read")
    @Operation(summary = "Lista todas as avaliações", description = "Retorna uma lista de todas as avaliações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de avaliações retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhuma avaliação encontrada")
    })
    public ResponseEntity<?> listarAvaliacoes() {
        List<Avaliacao> avaliacoes = avaliacaoService.getAvaliacoes();
        if (avaliacoes != null && !avaliacoes.isEmpty()) {
            return ResponseEntity.ok(avaliacoes);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma avaliação encontrada.");
        }
    }

    @PostMapping("/create")
    @Operation(summary = "Insere uma nova avaliação", description = "Adiciona uma nova avaliação ao sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Avaliação inserida com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição")
    })
    public ResponseEntity<?> inserirAvaliacao(@RequestBody Avaliacao avaliacao) {
        try {
            Avaliacao novaAvaliacao = avaliacaoService.saveAvaliacao(avaliacao);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaAvaliacao);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao inserir avaliação.");
        }
    }

    @GetMapping("/find/{id}")
    @Operation(summary = "Busca uma avaliação pelo ID", description = "Retorna uma avaliação pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Avaliação encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Avaliação não encontrada")
    })
    public ResponseEntity<?> buscarAvaliacaoPorId(@Parameter(description = "ID da avaliação a ser buscada") @PathVariable Long id) {
        Avaliacao avaliacao = avaliacaoService.getAvaliacaoById(id);
        if (avaliacao != null) {
            return ResponseEntity.ok(avaliacao);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Avaliação não encontrada.");
        }
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Exclui uma avaliação pelo ID", description = "Remove uma avaliação pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Avaliação excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Avaliação não encontrada")
    })
    public ResponseEntity<?> excluirAvaliacao(@Parameter(description = "ID da avaliação a ser excluída") @PathVariable Long id) {
        Avaliacao avaliacaoExcluida = avaliacaoService.deleteAvaliacaoById(id);
        if (avaliacaoExcluida != null) {
            return ResponseEntity.ok("Avaliação excluída com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Avaliação não encontrada.");
        }
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Atualiza uma avaliação pelo ID", description = "Atualiza uma avaliação existente pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Avaliação atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Avaliação não encontrada"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição")
    })
    public ResponseEntity<?> atualizarAvaliacao(@Parameter(description = "ID da avaliação a ser atualizada") @PathVariable Long id, @RequestBody Avaliacao avaliacaoAtualizada) {
        try {
            Avaliacao avaliacao = avaliacaoService.updateAvaliacao(id, avaliacaoAtualizada);
            if (avaliacao != null) {
                return ResponseEntity.ok(avaliacao);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Avaliação não encontrada.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atualizar avaliação.");
        }
    }
}

