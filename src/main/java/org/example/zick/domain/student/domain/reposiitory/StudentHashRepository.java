package org.example.zick.domain.student.domain.reposiitory;

import org.example.zick.domain.student.domain.StudentHash;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentHashRepository extends CrudRepository<StudentHash, String> {
}
