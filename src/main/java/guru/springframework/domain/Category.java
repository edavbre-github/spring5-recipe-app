package guru.springframework.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by David Brennan, 20/03/2019, 19:06
 *
 * As of 10/04/2019, Lombokising this class does not work and causes Spring context to fail to load
 * Update 12/04/2019, @Data does not work but using @Getter, @Setter and @NoArgsConstructor does work!!
 * Update 15/04/2019, Using the @EqualsAndHashCode(exclude={"recipes"}) seems to have fixed the problem.
 * Without this, HashCode gets into some sort of circular loop and stack overflow!!
 *
 * @author edavbre
 */
@Entity
//@Getter
//@Setter
//@NoArgsConstructor
@Data
@EqualsAndHashCode(exclude={"recipes"})
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;
}
