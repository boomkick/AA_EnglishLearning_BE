package com.englishlearning.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.englishlearning.dto.ErrorResponse;
import com.englishlearning.dto.VocabItemCreateRequest;
import com.englishlearning.dto.VocabItemDto;
import com.englishlearning.dto.VocabItemUpdateRequest;
import com.englishlearning.entity.AppUser;
import com.englishlearning.entity.VocabItem;
import com.englishlearning.repository.AppUserRepository;
import com.englishlearning.repository.VocabItemRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class VocabularyService {

    @Inject
    VocabItemRepository vocabItemRepository;

    @Inject
    AppUserRepository appUserRepository;

    public List<VocabItemDto> listVocabulary(UUID userId, String topic) {
        List<VocabItem> items = vocabItemRepository.findByUserId(userId);
        if (topic != null) {
            items = items.stream()
                    .filter(item -> item.getTopicTags() != null && item.getTopicTags().contains(topic))
                    .collect(Collectors.toList());
        }
        return items.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Transactional
    public VocabItemDto createVocabItem(UUID userId, VocabItemCreateRequest request) {
        AppUser user = appUserRepository.findById(userId);

        VocabItem item = new VocabItem();
        item.setUser(user);
        item.setTerm(request.getTerm());
        item.setTopicTags(request.getTopicTags());
        item.setCollocations(request.getCollocations());
        item.setExamples(request.getExamples());
        item.setUsageNotes(request.getUsageNotes());

        vocabItemRepository.persist(item);
        return toDto(item);
    }

    @Transactional
    public VocabItemDto updateVocabItem(UUID userId, UUID itemId, VocabItemUpdateRequest request) {
        VocabItem item = vocabItemRepository.findById(itemId);
        if (item == null || !item.getUser().getId().equals(userId)) {
            throw new WebApplicationException(
                    Response.status(404).entity(ErrorResponse.of(404, "Vocabulary item not found")).build());
        }

        if (request.getTerm() != null) {
            item.setTerm(request.getTerm());
        }
        if (request.getTopicTags() != null) {
            item.setTopicTags(request.getTopicTags());
        }
        if (request.getCollocations() != null) {
            item.setCollocations(request.getCollocations());
        }
        if (request.getExamples() != null) {
            item.setExamples(request.getExamples());
        }
        if (request.getUsageNotes() != null) {
            item.setUsageNotes(request.getUsageNotes());
        }

        vocabItemRepository.persist(item);
        return toDto(item);
    }

    @Transactional
    public void deleteVocabItem(UUID userId, UUID itemId) {
        VocabItem item = vocabItemRepository.findByIdAndUserId(itemId, userId);
        if (item == null) {
            throw new WebApplicationException(
                    Response.status(404).entity(ErrorResponse.of(404, "Vocabulary item not found")).build());
        }
        vocabItemRepository.delete(item);
    }

    private VocabItemDto toDto(VocabItem item) {
        VocabItemDto dto = new VocabItemDto();
        dto.setId(item.getId().toString());
        dto.setUserId(item.getUser().getId().toString());
        dto.setTerm(item.getTerm());
        dto.setTopicTags(item.getTopicTags());
        dto.setCollocations(item.getCollocations());
        dto.setExamples(item.getExamples());
        dto.setUsageNotes(item.getUsageNotes());
        return dto;
    }
}
