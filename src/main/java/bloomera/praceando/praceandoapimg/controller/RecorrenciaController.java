/*
 * Class: RecorrenciaController
 * Description: Controller for the Recorrencia entity.
 * Author: Camilla Ucci de Menezes
 * Creation Date: 14/10/2024
 * Last Updated: 16/10/2024
 */
package bloomera.praceando.praceandoapimg.controller;

import bloomera.praceando.praceandoapimg.model.Recorrencia;
import bloomera.praceando.praceandoapimg.service.RecorrenciaService;
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
@RequestMapping("/api/recorrencia")
@Tag(name = "Recorrencia", description = "Gerenciar as recorrências de eventos no sistema")
public class RecorrenciaController {

    private final RecorrenciaService recorrenciaService;

    @Autowired
    public RecorrenciaController(RecorrenciaService recorrenciaService) {
        this.recorrenciaService = recorrenciaService;
    }

    @GetMapping("/read")
    @Operation(summary = "Lista todas as recorrências de eventos", description = "Retorna uma lista de todas as recorrências de eventos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de recorrências retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhuma recorrência encontrada")
    })
    public ResponseEntity<?> listarRecorrencias() {
        List<Recorrencia> recorrencias = recorrenciaService.getRecorrencias();
        if (recorrencias != null && !recorrencias.isEmpty()) {
            return ResponseEntity.ok(recorrencias);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma recorrência encontrada.");
        }
    }

    @PostMapping("/create")
    @Operation(summary = "Cria uma nova recorrência", description = "Adiciona uma nova recorrência de evento no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Recorrência criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição")
    })
    public ResponseEntity<?> inserirRecorrencia(@RequestBody Recorrencia recorrencia) {
        try {
            Recorrencia novaRecorrencia = recorrenciaService.saveRecorrencia(recorrencia);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaRecorrencia);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao inserir recorrência.");
        }
    }

    @GetMapping("/find/{id}")
    @Operation(summary = "Busca uma recorrência pelo ID", description = "Retorna uma recorrência pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recorrência encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recorrência não encontrada")
    })
    public ResponseEntity<?> buscarRecorrenciaPorId(@Parameter(description = "ID da recorrência a ser buscada") @PathVariable Long id) {
        Recorrencia recorrencia = recorrenciaService.getRecorrenciaById(id);
        if (recorrencia != null) {
            return ResponseEntity.ok(recorrencia);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recorrência não encontrada.");
        }
    }

    @GetMapping("/find_evento/{idEvento}")
    @Operation(summary = "Busca uma recorrência pelo ID do evento", description = "Retorna uma recorrência pelo ID de seu evento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recorrência encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recorrência não encontrada")
    })
    public ResponseEntity<?> buscarRecorrenciaPorIdEvento(@Parameter(description = "ID da do evento da recorrência a ser buscada") @PathVariable Long idEvento) {
        Recorrencia recorrencia = recorrenciaService.getRecorrenciaByIdEvento(idEvento);
        if (recorrencia != null) {
            return ResponseEntity.ok(recorrencia);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recorrência não encontrada.");
        }
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Exclui uma recorrência pelo ID", description = "Remove uma recorrência de evento pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recorrência excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recorrência não encontrada")
    })
    public ResponseEntity<?> excluirRecorrencia(@Parameter(description = "ID da recorrência a ser excluída") @PathVariable Long id) {
        Recorrencia recorrenciaExcluida = recorrenciaService.deleteRecorrenciaById(id);
        if (recorrenciaExcluida != null) {
            return ResponseEntity.ok("Recorrência excluída com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recorrência não encontrada.");
        }
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Atualiza uma recorrência pelo ID", description = "Atualiza uma recorrência de evento existente pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recorrência atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recorrência não encontrada"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição")
    })
    public ResponseEntity<?> atualizarRecorrencia(@Parameter(description = "ID da recorrência a ser atualizada") @PathVariable Long id, @RequestBody Recorrencia recorrenciaAtualizada) {
        try {
            Recorrencia recorrencia = recorrenciaService.updateRecorrencia(id, recorrenciaAtualizada);
            if (recorrencia != null) {
                return ResponseEntity.ok(recorrencia);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recorrência não encontrada.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atualizar recorrência.");
        }
    }
}
