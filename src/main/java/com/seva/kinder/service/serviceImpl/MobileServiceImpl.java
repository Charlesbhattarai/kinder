package com.seva.kinder.service.serviceImpl;

import com.seva.kinder.entity.MobileDetail;
import com.seva.kinder.entity.Person;
import com.seva.kinder.repository.MobileRepository;
import com.seva.kinder.service.MobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MobileServiceImpl implements MobileService {
    @Autowired
    private MobileRepository mobileRepository;

    @Override
    public MobileDetail saveMobile(MobileDetail mobileDetail) {
            return mobileRepository.save(mobileDetail);
    }

    @Override
    public MobileDetail getByPersonId(Person person) {
        return mobileRepository.getMobileDetailByPersonId(person);
    }


}
