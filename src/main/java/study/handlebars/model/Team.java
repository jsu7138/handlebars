package study.handlebars.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id","name"})
public class Team {
    @Id @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
    private List<Member> members = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }
}
