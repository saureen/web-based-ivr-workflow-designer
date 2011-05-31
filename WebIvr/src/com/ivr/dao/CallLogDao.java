/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ivr.dao;

import com.ivr.model.CallLog;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;

/**
 *
 * @author swoosh
 */
public class CallLogDao extends AbstractSpringDao{

    public void delete(CallLog obj) {
        super.delete(obj);
    }

    public CallLog find(int id) {
        return (CallLog)super.find(CallLog.class, id);
    }

    public List<CallLog> findAll() {
        return super.findAll(CallLog.class);
    }

    @Override
    public List<CallLog> findByCriteria(DetachedCriteria criteria) {
        return super.findByCriteria(criteria);
    }

    @Override
    public List<CallLog> findByQuery(String query, Object[] values) {
        return super.findByQuery(query, values);
    }

    @Override
    public void saveOrUpdate(Object obj) {
        super.saveOrUpdate(obj);
    }

}
