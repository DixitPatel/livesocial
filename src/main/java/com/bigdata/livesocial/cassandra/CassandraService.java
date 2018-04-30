package com.bigdata.livesocial.cassandra;

import com.bigdata.livesocial.cassandra.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Dixit Patel
 */
@Service
public class CassandraService {

    @Autowired
    CassandraDaoImpl cassandraDao;

    public UUID addEventDetails(Event event){
        UUID eventId = cassandraDao.addEventDetails(event);
        return eventId;
    }
}
