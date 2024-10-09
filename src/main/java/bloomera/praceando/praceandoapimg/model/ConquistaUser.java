/*
 * Class: ConquistaUser
 * Description: Model for the ConquistaUser document.
 * Author: Camilla Ucci de Menezes
 * Creation Date: 03/10/2024
 * Last Updated: 07/10/2024
 */
package bloomera.praceando.praceandoapimg.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Document(collection = "conquista_usuario")
@Schema(description = "Representa a recorrência de eventos no sistema Praceando.")
public class ConquistaUser {

    @Id
    @Schema(description = "Identificador único da Conquista do Usuário.", example = "66fd42eb50119ee6449ea103")
    private String id;

    @Schema(description = "Identificador da conquista do usuário.", example = "1")
    @Field(name = "id_conquista_user")
    private Long idRecorrencia;

    @NotBlank(message = "O código do usuário que realizou a conquista ('cd_usuario') não pode estar vazio.")
    @Schema(description = "Código do usuário associado à conquista.", example = "1")
    @Field(name = "cd_usuario")
    private Long cdUsuario;

    @NotBlank(message = "O código da conquista realizada ('cd_conquista') não pode estar vazio.")
    @Schema(description = "Código da conquista associado ao usuário.", example = "1")
    @Field(name = "cd_conquista")
    private Long cdConquista;
}
