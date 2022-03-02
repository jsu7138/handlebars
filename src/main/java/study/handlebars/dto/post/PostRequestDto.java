package study.handlebars.dto.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import study.handlebars.model.Post;

@Data
@NoArgsConstructor
@ToString(of = {"member_id","member_name","post_id","title","content"})
public class PostRequestDto {
    private Long member_id;

    private String member_name;
    private Long post_id;
    private String title;
    private String content;

    @JsonIgnore
    private String secret;

    public PostRequestDto(String member_name, String title, String content) {
        this.member_name = member_name;
        this.title = title;
        this.content = content;
    }

    public PostRequestDto(Long member_id, Long post_id, String title, String content) {
        this.member_id = member_id;
        this.post_id = post_id;
        this.title = title;
        this.content = content;
    }


    @QueryProjection
    @Builder
    public PostRequestDto(Long member_id, String member_name, Long post_id, String title, String content) {
        this.member_id = member_id;
        this.member_name = member_name;
        this.post_id = post_id;
        this.title = title;
        this.content = content;
    }

    public Post toEntityByPost(){
        return Post.builder()
                .title(title)
                .value(content)
                .build();
    }
}
