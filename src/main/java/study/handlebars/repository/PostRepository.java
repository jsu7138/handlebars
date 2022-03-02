package study.handlebars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.handlebars.model.Post;
import study.handlebars.model.Team;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>,PostRepositoryCustom {
    List<Post> findListPostByMember_id(Long member_id);
}
