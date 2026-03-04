package com.englishlearning.repository;

import java.util.List;

import com.englishlearning.entity.MissionTemplate;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MissionTemplateRepository implements PanacheRepositoryBase<MissionTemplate, String> {

    public List<MissionTemplate> findBySkillId(String skillId) {
        return find("skill.id", skillId).list();
    }
}
