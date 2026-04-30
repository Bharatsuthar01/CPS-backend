package com.bank.cps.range.service;

import com.bank.cps.range.document.RangeSeriesDocument;
import com.bank.cps.range.dto.SaveRangeSeriesRequest;
import com.bank.cps.range.repository.RangeSeriesRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class RangeSeriesServiceImpl implements RangeSeriesService {
    private final RangeSeriesRepository repository;
    public RangeSeriesServiceImpl(RangeSeriesRepository repository) { this.repository = repository; }
    public RangeSeriesDocument save(SaveRangeSeriesRequest request) {
        RangeSeriesDocument doc = repository.findByCode(request.code()).orElseGet(RangeSeriesDocument::new);
        if (doc.getId() == null) doc.setId(UUID.randomUUID().toString());
        // Removed stub assignments (code, name, description)
        doc.setStatus("ACTIVE");
        return repository.save(doc);
    }
    public List<RangeSeriesDocument> findAll() { return repository.findAll(); }
}
