package com.bigdata.livesocial.cassandra;

import com.bigdata.livesocial.cassandra.model.Event;
import com.datastax.driver.core.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.cql.CqlTemplate;
import org.springframework.stereotype.Repository;


/**
 * @author Dixit Patel
 */

@Repository
public class CassandraDaoImpl {


    @Autowired
    private CassandraOperations cassandraOperations;

    public void addEventDetails(Event event){
       cassandraOperations.insert(event);
    }


}
