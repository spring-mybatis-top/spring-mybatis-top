-- Changeset config/liquibase/changelog/20111111000100_added_entity_HelloWorld.xml::20111111000100-1::jhipster
CREATE TABLE
  hello_world (
    id BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255),
    age INT,
    CONSTRAINT PK_HELLO_WORLD PRIMARY KEY (id)
  );
