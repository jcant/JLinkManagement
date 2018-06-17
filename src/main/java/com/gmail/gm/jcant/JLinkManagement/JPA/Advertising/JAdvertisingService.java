package com.gmail.gm.jcant.JLinkManagement.JPA.Advertising;

import java.util.Date;
import java.util.List;

public interface JAdvertisingService {
    void addAdvertising(JAdvertising advertising);
    void updateAdvertising(JAdvertising advertising);
    List<JAdvertising> getByCompany(String company);
    List<JAdvertising> getInDateAdvertising(Date date);
}
