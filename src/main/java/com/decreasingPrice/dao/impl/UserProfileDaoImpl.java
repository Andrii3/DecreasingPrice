package com.decreasingPrice.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.decreasingPrice.dao.AbstractDao;
import com.decreasingPrice.dao.UserProfileDao;
import com.decreasingPrice.model.UserProfile;

@Repository("userProfileDao")
public class UserProfileDaoImpl extends AbstractDao<Integer, UserProfile> implements UserProfileDao {

    public UserProfile findById(int id) {
        return getByKey(id);
    }

    public UserProfile findByType(String type) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("type", type));
        return (UserProfile) crit.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<UserProfile> findAll() {
        Criteria crit = createEntityCriteria();
        crit.addOrder(Order.asc("type"));
        return (List<UserProfile>) crit.list();
    }

}
