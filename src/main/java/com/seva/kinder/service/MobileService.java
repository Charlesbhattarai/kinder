package com.seva.kinder.service;

import com.seva.kinder.entity.MobileDetail;
import com.seva.kinder.entity.Person;

public interface MobileService {
    MobileDetail saveMobile(MobileDetail mobileDetail);
    MobileDetail getByPersonId(Person person);

}
