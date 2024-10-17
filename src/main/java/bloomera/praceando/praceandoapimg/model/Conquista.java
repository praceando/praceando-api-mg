/*
 * Class: Consquista
 * Description: Model for the Conquista document.
 * Author: Camilla Ucci de Menezes
 * Creation Date: 03/10/2024
 * Last Updated: 16/10/2024
 */
package bloomera.praceando.praceandoapimg.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Document(collection = "conquista")
@Schema(description = "Representa as conquistas a serem alcançadas no sistema Praceando.")
public class Conquista {
    @Id
    @Schema(description = "Identificador único da conquista.", example = "66fd42eb50119ee6449ea103")
    private String id;

    @Schema(description = "Identificador da conquista.", example = "1")
    @Field(name = "id_conquista")
    private Long idConquista;

    @NotBlank(message = "O nome da conquista ('nm_conquista') não pode estar vazio.")
    @Schema(description = "Nome da conquista.", example = "Aventurante do Parque")
    @Field(name = "nm_conquista")
    private String nmConquista;

    @NotBlank(message = "A descrição da conquista ('ds_conquista') não pode estar vazia.")
    @Schema(description = "Descrição da conquista.", example = "Participe de 5 eventos realizados em parques diferentes e explore a diversidade de atividades oferecidas.")
    @Field(name = "ds_conquista")
    private String dsConquista;

    @NotBlank(message = "O nome do tipo da conquista ('nm_tipo') não pode estar vazio.")
    @Schema(description = "Nome do tipo da conquista.", example = "Consumidor")
    @Field(name = "nm_tipo")
    private String nmTipo;

    @NotNull(message = "A quantidade de pólen da conquista ('qt_polen') não pode estar vazia.")
    @Schema(description = "Quantidade de pólen associada à conquista.", example = "50")
    @Field(name = "qt_polen")
    private Long qtPolen;
}
