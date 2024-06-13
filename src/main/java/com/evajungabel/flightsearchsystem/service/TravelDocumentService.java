package com.evajungabel.flightsearchsystem.service;

import com.evajungabel.flightsearchsystem.domain.TravelDocument;
import com.evajungabel.flightsearchsystem.repository.TravelDocumentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TravelDocumentService {

    private TravelDocumentRepository travelDocumentRepository;

    private ModelMapper modelMapper;

    @Autowired
    public TravelDocumentService(TravelDocumentRepository travelDocumentRepository, ModelMapper modelMapper) {
        this.travelDocumentRepository = travelDocumentRepository;
        this.modelMapper = modelMapper;
    }

    public void save(TravelDocument travelDocument) {
        travelDocumentRepository.save(travelDocument);
    }
}
