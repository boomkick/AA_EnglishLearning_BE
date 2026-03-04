package com.englishlearning.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.englishlearning.entity.MissionLog;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MissionLogRepository implements PanacheRepositoryBase<MissionLog, UUID> {

    public List<MissionLog> findByUserIdAndDateRange(UUID userId, LocalDate fromDate, LocalDate toDate) {
        return find("user.id = ?1 and logDate >= ?2 and logDate <= ?3",
                Sort.by("logDate").descending(), userId, fromDate, toDate).list();
    }

    public List<MissionLog> findByUserId(UUID userId) {
        return find("user.id", Sort.by("logDate").descending(), userId).list();
    }

    public List<MissionLog> findByUserIdAndLogDateAfter(UUID userId, LocalDate date) {
        return find("user.id = ?1 and logDate >= ?2",
                Sort.by("logDate").ascending(), userId, date).list();
    }
}
