/*
 * Class: ConquistaController
 * Description: Controller for the Conquista entity.
 * Author: Camilla Ucci de Menezes
 * Creation Date: 13/10/2024
 * Last Updated: 13/10/2024
 */
package bloomera.praceando.praceandoapimg.controller;

import bloomera.praceando.praceandoapimg.model.Conquista;
import bloomera.praceando.praceandoapimg.service.ConquistaService;
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
@RequestMapping("/api/conquista")
@Tag(name = "Conquista", description = "Gerenciar conquistas dos usuários no sistema")
public class ConquistaController {

    private final ConquistaService conquistaService;

    @Autowired
    public ConquistaController(ConquistaService conquistaService) {
        this.conquistaService = conquistaService;
    }

    @GetMapping("/read")
    @Operation(summary = "Lista todas as conquistas", description = "Retorna uma lista de todas as conquistas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de conquistas retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhuma conquista encontrada")
    })
    public ResponseEntity<?> listarConquistas() {
        List<Conquista> conquistas = conquistaService.getConquistas();
        if (conquistas != null && !conquistas.isEmpty()) {
            return ResponseEntity.ok(conquistas);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma conquista encontrada.");
        }
    }

    @PostMapping("/create")
    @Operation(summary = "Insere uma nova conquista", description = "Adiciona uma nova conquista ao sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Conquista inserida com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição")
    })
    public ResponseEntity<?> inserirConquista(@RequestBody Conquista conquista) {
        try {
            Conquista novaConquista = conquistaService.saveConquista(conquista);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaConquista);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao inserir conquista.");
        }
    }

    @GetMapping("/find/{id}")
    @Operation(summary = "Busca uma conquista pelo ID", description = "Retorna uma conquista pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Conquista encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Conquista não encontrada")
    })
    public ResponseEntity<?> buscarConquistaPorId(@Parameter(description = "ID da conquista a ser buscada") @PathVariable Long id) {
        Conquista conquista = conquistaService.getConquistaById(id);
        if (conquista != null) {
            return ResponseEntity.ok(conquista);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conquista não encontrada.");
        }
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Exclui uma conquista pelo ID", description = "Remove uma conquista pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Conquista excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Conquista não encontrada")
    })
    public ResponseEntity<?> excluirConquista(@Parameter(description = "ID da conquista a ser excluída") @PathVariable Long id) {
        Conquista conquistaExcluida = conquistaService.deleteConquistaById(id);
        if (conquistaExcluida != null) {
            return ResponseEntity.ok("Conquista excluída com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conquista não encontrada.");
        }
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Atualiza uma conquista pelo ID", description = "Atualiza uma conquista existente pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Conquista atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Conquista não encontrada"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição")
    })
    public ResponseEntity<?> atualizarConquista(@Parameter(description = "ID da conquista a ser atualizada") @PathVariable Long id, @RequestBody Conquista conquistaAtualizada) {
        try {
            Conquista conquista = conquistaService.updateConquista(id, conquistaAtualizada);
            if (conquista != null) {
                return ResponseEntity.ok(conquista);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conquista não encontrada.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atualizar conquista.");
        }
    }
}
