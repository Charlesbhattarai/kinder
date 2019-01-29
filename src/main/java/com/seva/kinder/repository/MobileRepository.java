package com.seva.kinder.repository;

import com.seva.kinder.entity.MobileDetail;
import com.seva.kinder.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MobileRepository extends JpaRepository<MobileDetail,Long> {
    @Query("select m from MobileDetail m where m.person=?1")
    MobileDetail getMobileDetailByPersonId(Person person);
}
