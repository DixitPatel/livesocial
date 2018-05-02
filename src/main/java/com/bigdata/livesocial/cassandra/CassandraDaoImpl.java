package com.bigdata.livesocial.cassandra;

import com.bigdata.livesocial.KafkaWebSocketController;
import com.bigdata.livesocial.cassandra.model.Event;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.utils.UUIDs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.cql.CqlTemplate;
import org.springframework.data.cassandra.core.query.Criteria;
import org.springframework.data.cassandra.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.UUID;


/**
 * @author Dixit Patel
 */

@Repository
@Component
public class CassandraDaoImpl {

    private static final Logger LOG = LoggerFactory.getLogger(CassandraDaoImpl.class);

    @Autowired
    private CassandraOperations cassandraOperations;

    public CassandraDaoImpl(CassandraOperations cassandraOperations) {
        this.cassandraOperations = cassandraOperations;
    }

    public UUID addEventDetails(Event event){
        UUID eventId = UUIDs.timeBased();
        event.setEventId(eventId);
        event.setCurrent_time(new Date(System.currentTimeMillis()));
        cassandraOperations.insert(event);

        //Test
        selectEvent(event);
        return eventId;

    }

    public Event selectEvent(Event event){
        Event retrievedEvent = cassandraOperations.selectOne(Query.query(Criteria.where("eventId").is(event.getEventId())), Event.class);
        LOG.info("retrieved from cassandra"+retrievedEvent.toString());
        return retrievedEvent;
    }

}
