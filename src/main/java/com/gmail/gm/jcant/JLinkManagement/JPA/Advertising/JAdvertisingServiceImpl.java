package com.gmail.gm.jcant.JLinkManagement.JPA.Advertising;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class JAdvertisingServiceImpl implements JAdvertisingService {

    @Autowired
    private JAdvertisingRepository advertisingRepository;

    @Override
    public void addAdvertising(JAdvertising advertising) {
        advertisingRepository.save(advertising);
    }

    @Override
    public void updateAdvertising(JAdvertising advertising) {
        advertisingRepository.save(advertising);
    }

    @Override
    public List<JAdvertising> getByCompany(String company) {
        List<JAdvertising> result = advertisingRepository.getByCompany(company);
        return result;
    }

    @Override
    public List<JAdvertising> getInDateAdvertising(Date date) {
        List<JAdvertising> result = advertisingRepository.getInDateAdvertising(date);
        return result;
    }

}
