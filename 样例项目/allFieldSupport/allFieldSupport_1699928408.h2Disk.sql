-- Changeset config/liquibase/changelog/20111111000100_added_entity_AllField.xml::20111111000100-1::jhipster
CREATE TABLE
  all_field (
    id BIGINT AUTO_INCREMENT NOT NULL,
    all_lowercase_regex VARCHAR(255) NOT NULL,
    name_integer INT NOT NULL,
    name_long BIGINT NOT NULL,
    name_big_decimal DECIMAL(21, 2) NOT NULL,
    name_float FLOAT4 NOT NULL,
    name_double DOUBLE NOT NULL,
    name_boolean BOOLEAN NOT NULL,
    name_local_date date NOT NULL,
    name_zoned_date_time TIMESTAMP NOT NULL,
    name_instant TIMESTAMP NOT NULL,
    name_blob BLOB NOT NULL,
    name_blob_content_type VARCHAR(255) NOT NULL,
    name_any_blob BLOB NOT NULL,
    name_any_blob_content_type VARCHAR(255) NOT NULL,
    name_image_blob BLOB NOT NULL,
    name_image_blob_content_type VARCHAR(255) NOT NULL,
    name_text_blob CLOB NOT NULL,
    CONSTRAINT PK_ALL_FIELD PRIMARY KEY (id)
  );
