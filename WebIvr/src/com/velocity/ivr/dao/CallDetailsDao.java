/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.velocity.ivr.dao;

import com.velocity.ivr.model.CallDetails;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;

/**
 *
 * @author swoosh
 */
public class CallDetailsDao extends AbstractSpringDao{

    public void delete(CallDetails obj) {
        super.delete(obj);
    }

    public CallDetails find(int id) {
        return (CallDetails)super.find(CallDetails.class, id);
    }

    public List<CallDetails> findAll() {
        return super.findAll(CallDetails.class);
    }

    @Override
    public List<CallDetails> findByCriteria(DetachedCriteria criteria) {
        return super.findByCriteria(criteria);
    }

    @Override
    public List<CallDetails> findByQuery(String query, Object[] values) {
        return super.findByQuery(query, values);
    }

    @Override
    public void saveOrUpdate(Object obj) {
        super.saveOrUpdate(obj);
    }
}
