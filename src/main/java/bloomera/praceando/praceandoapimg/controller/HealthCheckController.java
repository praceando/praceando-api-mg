package bloomera.praceando.praceandoapimg.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/health")
@Tag(name = "Health Check", description = "Verificar o status e manter a API ativa")
public class HealthCheckController {
    @Operation(summary = "Mantém a API viva", description = "Mentém a API viva usando o site https://keepalive.dashdashhard.com.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content)
    })
    @GetMapping("/keep-alive")
    public ResponseEntity<?> keepAlive() {
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
