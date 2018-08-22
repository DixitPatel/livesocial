CREATE KEYSPACE IF NOT EXISTS livesocial_t WITH replication = {'class':'SimpleStrategy', 'replication_factor':1};


CREATE TABLE event (
  eventId uuid PRIMARY KEY,
  coordinates text,
  name text,
  description text,
  current_time timestamp,
  start timestamp,
  end timestamp,
  userName text
);

CREATE TABLE user (
  name text PRIMARY KEY,


);