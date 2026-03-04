package com.englishlearning.resource;

import java.util.List;
import java.util.stream.Collectors;

import com.englishlearning.dto.SkillDto;
import com.englishlearning.entity.Skill;
import com.englishlearning.repository.SkillRepository;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/skills")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SkillResource {

    @Inject
    SkillRepository skillRepository;

    @GET
    public List<SkillDto> listSkills() {
        return skillRepository.listAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    private SkillDto toDto(Skill skill) {
        SkillDto dto = new SkillDto();
        dto.setId(skill.getId());
        dto.setLabel(skill.getLabel());
        return dto;
    }
}
