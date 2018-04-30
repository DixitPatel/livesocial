package com.bigdata.livesocial.cassandra;

import com.bigdata.livesocial.cassandra.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Dixit Patel
 */
@Service
public class CassandraService {

    @Autowired
    CassandraDaoImpl cassandraDao;

    public void addEventDetails(Event event){
        cassandraDao.addEventDetails(event);
    }
}
