package com.spring.rentingservice.repositories;

import com.spring.rentingservice.pojos.RentingDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface RentingDetailRepository extends JpaRepository<RentingDetail, Integer> {
    List<RentingDetail> getRentingDetailByStartDateAfterAndEndDateBefore(Date startDateAfter, Date endDateBefore);

    List<RentingDetail> getRentingDetailByStartDate(Date startDate);

    List<RentingDetail> getRentingDetailByStartDateAfterAndEndDateBeforeAndId_CarID(Date startDateAfter, Date endDateBefore, int idCarID);

    List<RentingDetail> getRentingDetailByStartDateBeforeAndEndDateAfterAndId_CarID(Date startDateBefore, Date endDateAfter, int idCarID);

    CharSequence getRentingDetailByStartDateLessThanAndEndDateGreaterThanEqual(Date startDateIsLessThan, Date endDateIsGreaterThan);

    CharSequence getRentingDetailByStartDateLessThanEqualAndEndDateGreaterThanEqual(Date startDateIsLessThan, Date endDateIsGreaterThan);

    CharSequence getRentingDetailByStartDateLessThanEqualAndEndDateGreaterThanEqualAndId_CarID(Date startDateIsLessThan, Date endDateIsGreaterThan, int idCarID);
}
