package pl.brzezinski.db;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.brzezinski.model.Result;

@Repository
public interface ResultRepository extends CrudRepository<Result, Long> {

}
