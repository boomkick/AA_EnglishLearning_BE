package com.englishlearning.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.englishlearning.dto.ErrorResponse;
import com.englishlearning.dto.MissionLogCreateRequest;
import com.englishlearning.dto.MissionLogDto;
import com.englishlearning.entity.AppUser;
import com.englishlearning.entity.MissionLog;
import com.englishlearning.entity.MissionTemplate;
import com.englishlearning.repository.AppUserRepository;
import com.englishlearning.repository.MissionLogRepository;
import com.englishlearning.repository.MissionTemplateRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class MissionLogService {

    @Inject
    MissionLogRepository missionLogRepository;

    @Inject
    MissionTemplateRepository missionTemplateRepository;

    @Inject
    AppUserRepository appUserRepository;

    @Transactional
    public MissionLogDto createLog(UUID userId, MissionLogCreateRequest request) {
        MissionTemplate template = missionTemplateRepository.findById(request.getMissionTemplateId());
        if (template == null) {
            throw new WebApplicationException(
                    Response.status(404).entity(ErrorResponse.of(404, "Mission template not found")).build());
        }

        AppUser user = appUserRepository.findById(userId);

        MissionLog log = new MissionLog();
        log.setUser(user);
        log.setMissionTemplate(template);
        log.setSkill(template.getSkill());
        log.setLogDate(request.getDate() != null ? LocalDate.parse(request.getDate()) : LocalDate.now());
        log.setDurationMinutes(request.getDurationMinutes() != null ? request.getDurationMinutes() : template.getEstMinutes());
        log.setXpEarned(template.getXp());
        log.setDifficulty(request.getDifficulty() != null ? request.getDifficulty() : template.getDifficulty());
        log.setNotes(request.getNotes());

        missionLogRepository.persist(log);

        user.setXpTotal(user.getXpTotal() + template.getXp());
        appUserRepository.persist(user);

        return toDto(log);
    }

    public List<MissionLogDto> listLogs(UUID userId, String fromDate, String toDate) {
        List<MissionLog> logs;
        if (fromDate != null && toDate != null) {
            logs = missionLogRepository.findByUserIdAndDateRange(userId, LocalDate.parse(fromDate), LocalDate.parse(toDate));
        } else {
            logs = missionLogRepository.findByUserId(userId);
        }
        return logs.stream().map(this::toDto).collect(Collectors.toList());
    }

    private MissionLogDto toDto(MissionLog log) {
        MissionLogDto dto = new MissionLogDto();
        dto.setId(log.getId().toString());
        dto.setUserId(log.getUser().getId().toString());
        dto.setDate(log.getLogDate().toString());
        dto.setMissionTemplateId(log.getMissionTemplate().getId());
        dto.setSkillId(log.getSkill().getId());
        dto.setDurationMinutes(log.getDurationMinutes());
        dto.setXpEarned(log.getXpEarned());
        dto.setDifficulty(log.getDifficulty());
        dto.setNotes(log.getNotes());
        return dto;
    }
}
