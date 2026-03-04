package com.englishlearning.resource;

import java.util.List;
import java.util.stream.Collectors;

import com.englishlearning.dto.MissionTemplateDto;
import com.englishlearning.entity.MissionTemplate;
import com.englishlearning.repository.MissionTemplateRepository;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/api/missions/templates")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MissionTemplateResource {

    @Inject
    MissionTemplateRepository missionTemplateRepository;

    @GET
    public List<MissionTemplateDto> listTemplates(@QueryParam("skillId") String skillId) {
        List<MissionTemplate> templates;
        if (skillId != null) {
            templates = missionTemplateRepository.findBySkillId(skillId);
        } else {
            templates = missionTemplateRepository.listAll();
        }
        return templates.stream().map(this::toDto).collect(Collectors.toList());
    }

    private MissionTemplateDto toDto(MissionTemplate template) {
        MissionTemplateDto dto = new MissionTemplateDto();
        dto.setId(template.getId());
        dto.setSkillId(template.getSkill() != null ? template.getSkill().getId() : null);
        dto.setTitle(template.getTitle());
        dto.setDescription(template.getDescription());
        dto.setXp(template.getXp());
        dto.setEstMinutes(template.getEstMinutes());
        dto.setDifficulty(template.getDifficulty());
        return dto;
    }
}
