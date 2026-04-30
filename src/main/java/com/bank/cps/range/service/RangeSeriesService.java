package com.bank.cps.range.service;

import com.bank.cps.range.document.RangeSeriesDocument;
import com.bank.cps.range.dto.SaveRangeSeriesRequest;
import java.util.List;

public interface RangeSeriesService {
    RangeSeriesDocument save(SaveRangeSeriesRequest request);
    List<RangeSeriesDocument> findAll();
}
