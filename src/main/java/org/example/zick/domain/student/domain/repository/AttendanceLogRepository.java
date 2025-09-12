package org.example.zick.domain.student.domain.repository;

import org.example.zick.domain.student.domain.AttendanceLog;
import org.springframework.data.repository.CrudRepository;

public interface AttendanceLogRepository extends CrudRepository<AttendanceLog,Long> {
}
