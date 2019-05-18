package guru.springframework.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by David Brennan, 15/05/2019, 17:49
 *
 * @author EDAVBRE
 */
@Getter
@Setter
@NoArgsConstructor
public class NotesCommand {
    private Long id;
//    private RecipeCommand recipe;
    private String recipeNotes;
}
