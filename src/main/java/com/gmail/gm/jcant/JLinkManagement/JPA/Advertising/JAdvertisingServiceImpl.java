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
    public void deleteById(long id) throws JAdvertisingException {
        if (!advertisingRepository.existsById(id)){
            throw new JAdvertisingException("Can't delete: NO Advertising with such id="+id);
        }
        advertisingRepository.deleteById(id);
    }

    @Override
    public void updateAdvertising(JAdvertising advertising) {
        advertisingRepository.save(advertising);
    }

    @Override
    public List<JAdvertising> getByCompany(String company) {
        return advertisingRepository.getByCompany(company);
    }

    @Override
    public List<JAdvertising> getInDateAdvertising(Date date) {
        return advertisingRepository.getInDateAdvertising(date);
    }

    @Override
    public JAdvertising getById(long id) throws JAdvertisingException {

        if (!advertisingRepository.existsById(id)) {
            throw new JAdvertisingException("NO Advertising found for id="+id);
        }
        return advertisingRepository.getById(id);
    }

    @Override
    public void save(JAdvertising article) {
        advertisingRepository.save(article);
    }

}
