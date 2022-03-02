package study.handlebars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import study.handlebars.model.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
    Optional<Member> findByName(String name);

    @Query(value = "select m from Member m join fetch m.posts")
    List<Member> findFetchByMember();
}
