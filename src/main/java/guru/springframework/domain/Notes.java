package guru.springframework.domain;

import lombok.*;

import javax.persistence.*;

/**
 * Created by David Brennan, 19/03/2019, 18:23
 *
 * As of 10/04/2019, Lombokising this class DOES work unlike the Category class. I have no idea why!!
 * Update 15/04/2019, Using the @EqualsAndHashCode(exclude={"recipe"}) seems to have fixed the problem.
 * Without this, HashCode gets into some sort of circular loop and stack overflow!!
 *
 * @author edavbre
 */
@Entity
@Data
@EqualsAndHashCode(exclude={"recipe"})
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Recipe recipe;

    @Lob
    private String recipeNotes;
}
