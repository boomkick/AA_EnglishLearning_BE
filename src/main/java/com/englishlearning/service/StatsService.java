package com.englishlearning.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.englishlearning.dto.UserStatsDto;
import com.englishlearning.entity.MissionLog;
import com.englishlearning.repository.MissionLogRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class StatsService {

    @Inject
    MissionLogRepository missionLogRepository;

    public UserStatsDto getUserStats(UUID userId) {
        List<MissionLog> allLogs = missionLogRepository.findByUserId(userId);

        int streak = calculateStreak(allLogs);

        LocalDate monday = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        int weeklyXpEarned = allLogs.stream()
                .filter(log -> !log.getLogDate().isBefore(monday))
                .mapToInt(MissionLog::getXpEarned)
                .sum();

        List<MissionLog> last7dLogs = missionLogRepository.findByUserIdAndLogDateAfter(userId, LocalDate.now().minusDays(7));
        Map<String, Integer> skillDistribution7d = new HashMap<>();
        for (MissionLog log : last7dLogs) {
            String skillId = log.getSkill().getId();
            skillDistribution7d.merge(skillId, log.getXpEarned(), Integer::sum);
        }

        UserStatsDto dto = new UserStatsDto();
        dto.setStreak(streak);
        dto.setWeeklyXpEarned(weeklyXpEarned);
        dto.setSkillDistribution7d(skillDistribution7d);
        return dto;
    }

    private int calculateStreak(List<MissionLog> logs) {
        if (logs.isEmpty()) {
            return 0;
        }

        Set<LocalDate> logDates = logs.stream()
                .map(MissionLog::getLogDate)
                .collect(Collectors.toSet());

        int streak = 0;
        LocalDate date = LocalDate.now();

        while (logDates.contains(date)) {
            streak++;
            date = date.minusDays(1);
        }

        return streak;
    }
}
