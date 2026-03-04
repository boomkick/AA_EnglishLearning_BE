package com.englishlearning.repository;

import com.englishlearning.entity.Skill;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SkillRepository implements PanacheRepositoryBase<Skill, String> {
}
