package com.gmail.gm.jcant.JLinkManagement.JPA.Advertising;

import java.util.Date;
import java.util.List;

public interface JAdvertisingService {
    void addAdvertising(JAdvertising advertising);
    void deleteById(long id) throws JAdvertisingException;
    void updateAdvertising(JAdvertising advertising);
    List<JAdvertising> getByCompany(String company);
    List<JAdvertising> getInDateAdvertising(Date date);
    JAdvertising getById(long id) throws JAdvertisingException;

    void save(JAdvertising advertising);
}
