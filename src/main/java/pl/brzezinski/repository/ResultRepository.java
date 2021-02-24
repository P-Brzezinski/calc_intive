package pl.brzezinski.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.brzezinski.model.Result;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {

    List<Result> findAllByDateTimeAfterAndDateTimeBefore(LocalDateTime after, LocalDateTime before, Pageable paging);
}
