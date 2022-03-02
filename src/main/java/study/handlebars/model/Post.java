package study.handlebars.model;

import lombok.*;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id","title","value"})
public class Post extends BaseTimeEntity {
    @Id @GeneratedValue
    private Long id;
    private String title;
    private String value;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Builder
    public Post(String title, String value) {
        this.title = title;
        this.value = value;
    }
}
