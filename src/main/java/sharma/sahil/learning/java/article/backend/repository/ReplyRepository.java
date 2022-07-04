package sharma.sahil.learning.java.article.backend.repository;

import sharma.sahil.learning.java.article.backend.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
