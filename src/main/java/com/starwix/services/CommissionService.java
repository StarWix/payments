package com.starwix.services;

import com.starwix.entities.Commission;
import com.starwix.entities.CommissionList;
import com.starwix.repositories.CommissionRepository;

import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

/**
 * Created by starwix on 19.9.16.
 */
public class CommissionService {
    private final CommissionRepository commissionRepository;

    @Inject
    public CommissionService(final CommissionRepository commissionRepository) {
        this.commissionRepository = commissionRepository;
    }

    public CommissionList parseFile(final File commissionsFile) {
        try {
            final JAXBContext jc = JAXBContext.newInstance(CommissionList.class);
            final Unmarshaller unmarshaller = jc.createUnmarshaller();
            return (CommissionList) unmarshaller.unmarshal(commissionsFile);
        } catch (final JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAll() {
        commissionRepository.deleteAll();
    }

    public void save(final List<Commission> commissions) {
        commissionRepository.save(commissions);
    }
}
