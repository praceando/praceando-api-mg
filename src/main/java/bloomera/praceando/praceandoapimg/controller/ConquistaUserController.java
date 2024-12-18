/*
 * Class: ConquistaUserController
 * Description: Controller for the ConquistaUser entity.
 * Author: Camilla Ucci de Menezes
 * Creation Date: 14/10/2024
 * Last Updated: 16/10/2024
 */
package bloomera.praceando.praceandoapimg.controller;

import bloomera.praceando.praceandoapimg.model.Conquista;
import bloomera.praceando.praceandoapimg.model.ConquistaUser;
import bloomera.praceando.praceandoapimg.service.ConquistaUserService;
import bloomera.praceando.praceandoapimg.service.CounterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conquista_user")
@Tag(name = "ConquistaUser", description = "Gerenciar as conquistas associadas aos usuários no sistema")
public class ConquistaUserController {

    private final ConquistaUserService conquistaUserService;
    private final CounterService counterService;

    @Autowired
    public ConquistaUserController(ConquistaUserService conquistaUserService, CounterService counterService) {
        this.conquistaUserService = conquistaUserService;
        this.counterService = counterService;
    }

    @GetMapping("/read/{cdUsuario}")
    @Operation(summary = "Lista todas as conquistas do usuário", description = "Retorna uma lista de todas as conquistas dos usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de conquistas do usuário retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhuma conquista do usuário encontrada")
    })
    public ResponseEntity<?> listarConquistasUsuario(@Parameter(description = "ID do usuário cujas conquistas serão retornadas") @PathVariable Long cdUsuario) {
        List<Conquista> conquistas = conquistaUserService.getConquistaUser(cdUsuario);
        if (conquistas != null && !conquistas.isEmpty()) {
            return ResponseEntity.ok(conquistas);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma conquista de usuário encontrada.");
        }
    }

    @PostMapping("/create")
    @Operation(summary = "Associa uma nova conquista a um usuário", description = "Adiciona uma nova conquista ao usuário no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Conquista de usuário inserida com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição")
    })
    public ResponseEntity<?> inserirConquistaUsuario(@RequestBody
                                                         @Schema(example = "{\n" +
                                                                 "  \"cdUsuario\": 1,\n" +
                                                                 "  \"cdConquista\": 1\n" +
                                                                 "}")ConquistaUser conquistaUser) {
        try {
            Long novoId = counterService.getNextSequenceValue("conquista_user");
            conquistaUser.setIdConquistaUser(novoId);

            ConquistaUser novaConquistaUser = conquistaUserService.saveConquistaUser(conquistaUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaConquistaUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao inserir conquista de usuário.");
        }
    }

    @GetMapping("/find/{id}")
    @Operation(summary = "Busca uma conquista de usuário pelo ID", description = "Retorna uma conquista de usuário pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Conquista de usuário encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Conquista de usuário não encontrada")
    })
    public ResponseEntity<?> buscarConquistaUsuarioPorId(@Parameter(description = "ID da conquista de usuário a ser buscada") @PathVariable Long id) {
        ConquistaUser conquistaUser = conquistaUserService.getConquistaUserById(id);
        if (conquistaUser != null) {
            return ResponseEntity.ok(conquistaUser);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conquista de usuário não encontrada.");
        }
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Exclui uma conquista de usuário pelo ID", description = "Remove uma conquista de usuário pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Conquista de usuário excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Conquista de usuário não encontrada")
    })
    public ResponseEntity<?> excluirConquistaUsuario(@Parameter(description = "ID da conquista de usuário a ser excluída") @PathVariable Long id) {
        ConquistaUser conquistaUserExcluida = conquistaUserService.deleteConquistaUserById(id);
        if (conquistaUserExcluida != null) {
            return ResponseEntity.ok("Conquista de usuário excluída com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conquista de usuário não encontrada.");
        }
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Atualiza uma conquista de usuário pelo ID", description = "Atualiza uma conquista de usuário existente pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Conquista de usuário atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Conquista de usuário não encontrada"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição")
    })
    public ResponseEntity<?> atualizarConquistaUsuario(@Parameter(description = "ID da conquista de usuário a ser atualizada") @PathVariable Long id, @RequestBody ConquistaUser conquistaUserAtualizada) {
        try {
            ConquistaUser conquistaUser = conquistaUserService.updateConquistaUser(id, conquistaUserAtualizada);
            if (conquistaUser != null) {
                return ResponseEntity.ok(conquistaUser);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conquista de usuário não encontrada.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atualizar conquista de usuário.");
        }
    }
}
