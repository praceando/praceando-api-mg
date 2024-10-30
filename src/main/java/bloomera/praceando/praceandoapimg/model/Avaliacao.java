/*
 * Class: Avaliacao
 * Description: Model for the Avaliacao document.
 * Author: Camilla Ucci de Menezes
 * Creation Date: 03/10/2024
 * Last Updated: 29/10/2024
 */
package bloomera.praceando.praceandoapimg.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Document(collection = "avaliacao")
@Schema(description = "Representa a avaliação de eventos no sistema Praceando.")
public class Avaliacao {
    @Id
    @Schema(description = "Identificador único gerado pelo MongoDB.", example = "66fd42eb50119ee6449ea103")
    private String id;

    @Schema(description = "Identificador lógico da avaliação.", example = "1")
    @Field(name = "id_avaliacao")
    private Long idAvaliacao;

    @NotNull(message = "O código do evento da avaliação ('cd_evento') não pode estar vazio.")
    @Schema(description = "Código do evento associado à avaliação.", example = "1")
    @Field(name = "cd_evento")
    private Long cdEvento;

    @NotNull(message = "O código do usuário da avaliação ('cd_usuario') não pode estar vazio.")
    @Schema(description = "Código do usuario associado à avaliação.", example = "1")
    @Field(name = "cd_usuario")
    private Long cdUsuario;

    @NotNull(message = "A nota da avaliação ('nota') não pode estar vazia.")
    @Schema(description = "Nota associada à avaliação.", example = "5")
    @Field(name = "nota")
    private Long nrNota;

    @Schema(description = "Comentário da avaliação.", example = "Evento incrível! Superou minhas expectativas.")
    @Field(name = "comentario")
    private String dsComentario;

    @Schema(description = "Data e hora da última atualização da avaliação.", example = "2024-08-18T10:00:00")
    @Field(name = "dt_atualizacao")
    private LocalDateTime dtAtualizacao;
}
