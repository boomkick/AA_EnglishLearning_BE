package com.englishlearning.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "app_user")
public class AppUser {

    @Id
    private UUID id;

    @Column(unique = true)
    private String email;

    private String passwordHash;

    private String displayName;

    @Column(columnDefinition = "int default 0")
    private int xpTotal;

    @Column(columnDefinition = "int default 450")
    private int weeklyXpTarget = 450;

    @Column(columnDefinition = "int default 0")
    private int rewardPointsUsed;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public AppUser() {
    }

    @PrePersist
    public void prePersist() {
        if (id == null) {
            id = UUID.randomUUID();
        }
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getXpTotal() {
        return xpTotal;
    }

    public void setXpTotal(int xpTotal) {
        this.xpTotal = xpTotal;
    }

    public int getWeeklyXpTarget() {
        return weeklyXpTarget;
    }

    public void setWeeklyXpTarget(int weeklyXpTarget) {
        this.weeklyXpTarget = weeklyXpTarget;
    }

    public int getRewardPointsUsed() {
        return rewardPointsUsed;
    }

    public void setRewardPointsUsed(int rewardPointsUsed) {
        this.rewardPointsUsed = rewardPointsUsed;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
