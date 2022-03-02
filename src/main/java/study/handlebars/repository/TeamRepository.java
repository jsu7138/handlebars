package study.handlebars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.handlebars.model.Member;
import study.handlebars.model.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
