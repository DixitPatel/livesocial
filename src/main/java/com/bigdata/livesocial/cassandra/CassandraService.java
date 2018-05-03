package com.bigdata.livesocial.cassandra;

import com.bigdata.livesocial.cassandra.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Dixit Patel
 */
@Service
@Component
public class CassandraService {

    @Autowired
    CassandraDaoImpl cassandraDaoImpl;

    public UUID addEventDetails(Event event){
        UUID eventId = cassandraDaoImpl.addEventDetails(event);
        return eventId;
    }
}
