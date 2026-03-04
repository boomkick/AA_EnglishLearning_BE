package com.englishlearning.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.englishlearning.dto.CorrectionDto;
import com.englishlearning.dto.ErrorResponse;
import com.englishlearning.dto.FeedbackListDto;
import com.englishlearning.dto.SpeakingChecklistDto;
import com.englishlearning.dto.SpeakingFeedbackCreateRequest;
import com.englishlearning.dto.SpeakingFeedbackDto;
import com.englishlearning.dto.SpeakingFeedbackUpdateRequest;
import com.englishlearning.dto.WritingFeedbackCreateRequest;
import com.englishlearning.dto.WritingFeedbackDto;
import com.englishlearning.dto.WritingFeedbackUpdateRequest;
import com.englishlearning.entity.AppUser;
import com.englishlearning.entity.Correction;
import com.englishlearning.entity.SpeakingChecklist;
import com.englishlearning.entity.SpeakingFeedback;
import com.englishlearning.entity.WritingFeedback;
import com.englishlearning.repository.AppUserRepository;
import com.englishlearning.repository.SpeakingFeedbackRepository;
import com.englishlearning.repository.WritingFeedbackRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class FeedbackService {

    @Inject
    WritingFeedbackRepository writingFeedbackRepository;

    @Inject
    SpeakingFeedbackRepository speakingFeedbackRepository;

    @Inject
    AppUserRepository appUserRepository;

    public FeedbackListDto listFeedback(UUID userId) {
        List<WritingFeedbackDto> writingDtos = writingFeedbackRepository.findByUserId(userId)
                .stream().map(this::toWritingDto).collect(Collectors.toList());
        List<SpeakingFeedbackDto> speakingDtos = speakingFeedbackRepository.findByUserId(userId)
                .stream().map(this::toSpeakingDto).collect(Collectors.toList());

        FeedbackListDto result = new FeedbackListDto();
        result.setWriting(writingDtos);
        result.setSpeaking(speakingDtos);
        return result;
    }

    @Transactional
    public WritingFeedbackDto createWritingFeedback(UUID userId, WritingFeedbackCreateRequest request) {
        AppUser user = appUserRepository.findById(userId);

        WritingFeedback feedback = new WritingFeedback();
        feedback.setUser(user);
        feedback.setCreatedAt(LocalDate.now());
        feedback.setTitle(request.getTitle());
        feedback.setPrompt(request.getPrompt());
        feedback.setTextContent(request.getText());
        feedback.setCorrections(toCorrectionEntities(request.getCorrections()));
        feedback.setRepeatedMistakeTags(request.getRepeatedMistakeTags());

        writingFeedbackRepository.persist(feedback);
        return toWritingDto(feedback);
    }

    @Transactional
    public WritingFeedbackDto updateWritingFeedback(UUID userId, UUID feedbackId, WritingFeedbackUpdateRequest request) {
        WritingFeedback feedback = writingFeedbackRepository.findById(feedbackId);
        if (feedback == null || !feedback.getUser().getId().equals(userId)) {
            throw new WebApplicationException(
                    Response.status(404).entity(ErrorResponse.of(404, "Writing feedback not found")).build());
        }

        if (request.getTitle() != null) {
            feedback.setTitle(request.getTitle());
        }
        if (request.getPrompt() != null) {
            feedback.setPrompt(request.getPrompt());
        }
        if (request.getText() != null) {
            feedback.setTextContent(request.getText());
        }
        if (request.getCorrections() != null) {
            feedback.setCorrections(toCorrectionEntities(request.getCorrections()));
        }
        if (request.getRepeatedMistakeTags() != null) {
            feedback.setRepeatedMistakeTags(request.getRepeatedMistakeTags());
        }

        writingFeedbackRepository.persist(feedback);
        return toWritingDto(feedback);
    }

    @Transactional
    public SpeakingFeedbackDto createSpeakingFeedback(UUID userId, SpeakingFeedbackCreateRequest request) {
        AppUser user = appUserRepository.findById(userId);

        SpeakingFeedback feedback = new SpeakingFeedback();
        feedback.setUser(user);
        feedback.setCreatedAt(LocalDate.now());
        feedback.setTitle(request.getTitle());
        feedback.setAudioFileName(request.getAudioFileName());
        feedback.setChecklist(toChecklistEntity(request.getChecklist()));
        feedback.setComments(request.getComments());
        feedback.setIssueTags(request.getIssueTags());

        speakingFeedbackRepository.persist(feedback);
        return toSpeakingDto(feedback);
    }

    @Transactional
    public SpeakingFeedbackDto updateSpeakingFeedback(UUID userId, UUID feedbackId, SpeakingFeedbackUpdateRequest request) {
        SpeakingFeedback feedback = speakingFeedbackRepository.findById(feedbackId);
        if (feedback == null || !feedback.getUser().getId().equals(userId)) {
            throw new WebApplicationException(
                    Response.status(404).entity(ErrorResponse.of(404, "Speaking feedback not found")).build());
        }

        if (request.getTitle() != null) {
            feedback.setTitle(request.getTitle());
        }
        if (request.getAudioFileName() != null) {
            feedback.setAudioFileName(request.getAudioFileName());
        }
        if (request.getChecklist() != null) {
            feedback.setChecklist(toChecklistEntity(request.getChecklist()));
        }
        if (request.getComments() != null) {
            feedback.setComments(request.getComments());
        }
        if (request.getIssueTags() != null) {
            feedback.setIssueTags(request.getIssueTags());
        }

        speakingFeedbackRepository.persist(feedback);
        return toSpeakingDto(feedback);
    }

    private WritingFeedbackDto toWritingDto(WritingFeedback feedback) {
        WritingFeedbackDto dto = new WritingFeedbackDto();
        dto.setId(feedback.getId().toString());
        dto.setUserId(feedback.getUser().getId().toString());
        dto.setCreatedAt(feedback.getCreatedAt() != null ? feedback.getCreatedAt().toString() : null);
        dto.setTitle(feedback.getTitle());
        dto.setPrompt(feedback.getPrompt());
        dto.setText(feedback.getTextContent());
        dto.setCorrections(toCorrectionDtos(feedback.getCorrections()));
        dto.setRepeatedMistakeTags(feedback.getRepeatedMistakeTags());
        return dto;
    }

    private SpeakingFeedbackDto toSpeakingDto(SpeakingFeedback feedback) {
        SpeakingFeedbackDto dto = new SpeakingFeedbackDto();
        dto.setId(feedback.getId().toString());
        dto.setUserId(feedback.getUser().getId().toString());
        dto.setCreatedAt(feedback.getCreatedAt() != null ? feedback.getCreatedAt().toString() : null);
        dto.setTitle(feedback.getTitle());
        dto.setAudioFileName(feedback.getAudioFileName());
        dto.setChecklist(toChecklistDto(feedback.getChecklist()));
        dto.setComments(feedback.getComments());
        dto.setIssueTags(feedback.getIssueTags());
        return dto;
    }

    private List<Correction> toCorrectionEntities(List<CorrectionDto> dtos) {
        if (dtos == null) {
            return new ArrayList<>();
        }
        return dtos.stream().map(d -> new Correction(d.getFrom(), d.getTo(), d.getNote())).collect(Collectors.toList());
    }

    private List<CorrectionDto> toCorrectionDtos(List<Correction> entities) {
        if (entities == null) {
            return new ArrayList<>();
        }
        return entities.stream().map(e -> {
            CorrectionDto dto = new CorrectionDto();
            dto.setFrom(e.getFrom());
            dto.setTo(e.getTo());
            dto.setNote(e.getNote());
            return dto;
        }).collect(Collectors.toList());
    }

    private SpeakingChecklist toChecklistEntity(SpeakingChecklistDto dto) {
        if (dto == null) {
            return null;
        }
        return new SpeakingChecklist(
                dto.getEndingSounds() != null && dto.getEndingSounds(),
                dto.getLinking() != null && dto.getLinking(),
                dto.getStress() != null && dto.getStress(),
                dto.getClarity() != null && dto.getClarity());
    }

    private SpeakingChecklistDto toChecklistDto(SpeakingChecklist entity) {
        if (entity == null) {
            return null;
        }
        SpeakingChecklistDto dto = new SpeakingChecklistDto();
        dto.setEndingSounds(entity.isEndingSounds());
        dto.setLinking(entity.isLinking());
        dto.setStress(entity.isStress());
        dto.setClarity(entity.isClarity());
        return dto;
    }
}
