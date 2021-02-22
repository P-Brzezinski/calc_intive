package pl.brzezinski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.brzezinski.model.Result;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {

}
