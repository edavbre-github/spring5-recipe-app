package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by David Brennan, 15/05/2019, 21:09
 *
 * @author EDAVBRE
 */
@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {

    @Synchronized
    @Nullable
    @Override
    public NotesCommand convert(Notes notes) {
        if (notes == null) {
            return null;
        }

        NotesCommand command = new NotesCommand();
        command.setId(notes.getId());
        command.setRecipeNotes(notes.getRecipeNotes());
//        command.setRecipe();
        return command;
    }
}
