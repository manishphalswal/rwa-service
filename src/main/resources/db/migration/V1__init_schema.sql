
	DROP SEQUENCE IF EXISTS t_complaint_seq;
	DROP SEQUENCE IF EXISTS t_document_seq;
	DROP SEQUENCE IF EXISTS t_event_seq;
	DROP SEQUENCE IF EXISTS t_user_address_seq;
	DROP SEQUENCE IF EXISTS t_user_seq;
	DROP SEQUENCE IF EXISTS t_user_session_seq;
	
	DROP TABLE IF EXISTS t_complaint CASCADE ;
	DROP TABLE IF EXISTS t_document CASCADE ;
	DROP TABLE IF EXISTS t_event CASCADE ;
	DROP TABLE IF EXISTS t_user_address CASCADE ;
	DROP TABLE IF EXISTS t_user CASCADE ;
	DROP TABLE IF EXISTS t_user_session CASCADE ;
	
	DROP TABLE IF EXISTS m_complaint_type CASCADE;
	DROP TABLE IF EXISTS m_doc_type CASCADE ;
	DROP TABLE IF EXISTS m_event_type CASCADE ;
	DROP TABLE IF EXISTS m_state CASCADE ;
	DROP TABLE IF EXISTS m_village CASCADE ;
	
	-------------------------SEQUENCE CREATION---------------------------
	
	CREATE SEQUENCE t_complaint_seq INCREMENT BY 1 MINVALUE 1;

	COMMENT ON SEQUENCE t_complaint_seq IS 'Sequence to generate primary keys for t_complaint table';

	CREATE SEQUENCE t_document_seq INCREMENT BY 1 MINVALUE 1;

	COMMENT ON SEQUENCE t_document_seq IS 'Sequence to generate primary keys for t_document table';

	CREATE SEQUENCE t_event_seq INCREMENT BY 1 MINVALUE 1;

	COMMENT ON SEQUENCE t_event_seq IS 'Sequence to generate primary keys for t_event table';

	CREATE SEQUENCE t_user_address_seq INCREMENT BY 1 MINVALUE 1;

	COMMENT ON SEQUENCE t_user_address_seq IS 'Sequence to generate primary keys for t_user_address table';

	CREATE SEQUENCE t_user_seq INCREMENT BY 1 MINVALUE 1;

	COMMENT ON SEQUENCE t_user_seq IS 'Sequence to generate primary keys for t_user table';

	CREATE SEQUENCE t_user_session_seq INCREMENT BY 1 MINVALUE 1;

	COMMENT ON SEQUENCE t_user_session_seq IS 'Sequence to generate primary keys for t_user_session table';
	
	-------------------------MASTER TABLE CREATION---------------------------

	CREATE TABLE m_complaint_type ( 
		id                   bigint  NOT NULL ,
		complaint_type       varchar(100)  NOT NULL ,
		created_date         timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL ,
		created_by           varchar(20)  NOT NULL ,
		CONSTRAINT unq_m_complaint_type_id UNIQUE ( id ) 
	 );

	COMMENT ON COLUMN m_complaint_type.id IS 'Primary key of the table';

	CREATE  TABLE m_doc_type ( 
		id                   bigint  NOT NULL ,
		doc_type             varchar(20)  NOT NULL ,
		created_date         timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL ,
		CONSTRAINT pk_m_doc_type_id PRIMARY KEY ( id )
	 );

	COMMENT ON COLUMN m_doc_type.id IS 'Primary key of the table';

	CREATE TABLE m_event_type ( 
		id                   bigint  NOT NULL ,
		event_name           varchar(100)  NOT NULL ,
		created_date         timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL ,
		created_by           varchar(20)  NOT NULL ,
		CONSTRAINT pk_m_event_type_id PRIMARY KEY ( id )
	 );

	COMMENT ON COLUMN m_event_type.id IS 'Primary key of the table';

	CREATE TABLE m_state ( 
		id                   bigint  NOT NULL ,
		state_name           varchar(50)  NOT NULL ,
		country              varchar(50)  NOT NULL ,
		created_date         timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL ,
		CONSTRAINT pk_m_state_id PRIMARY KEY ( id )
	 );

	COMMENT ON TABLE m_state IS 'Stores reference data for the states of a country';

	COMMENT ON COLUMN m_state.id IS 'Primary key of table';

	CREATE TABLE m_village ( 
		id                   bigint  NOT NULL ,
		village_name         varchar(50)  NOT NULL ,
		state_id             bigint  NOT NULL ,
		created_date         timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL ,
		CONSTRAINT pk_m_village_id PRIMARY KEY ( id )
	 );

	COMMENT ON TABLE m_village IS 'Stores reference data for the villages of a state';

	COMMENT ON COLUMN m_village.id IS 'Primary key of the table';
	
	-------------------------TRANSACTION TABLE CREATION---------------------------
	
	CREATE TABLE t_complaint ( 
		id                   bigint  NOT NULL ,
		complaint_type_id    bigint  NOT NULL ,
		complaint_village_id bigint  NOT NULL ,
		complaint_location   varchar(100)  NOT NULL ,
		complaint_details    varchar(1000)  NOT NULL ,
		complaint_doc_id     bigint   ,
		complainant_name     varchar(100)  NOT NULL ,
		complainant_mobile_no varchar(10)  NOT NULL ,
		complainant_email_id varchar(50)   ,
		same_address         boolean  NOT NULL ,
		complainant_address  varchar(200)   ,
		complainant_village_id bigint   ,
		complainant_pin_code varchar(6)   ,
		complaint_no         varchar(20)  NOT NULL ,
		pgms_complaint_no    varchar(50)   ,
		created_date         timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL ,
		created_by           varchar(20)  NOT NULL ,
		CONSTRAINT pk_t_complaint_id PRIMARY KEY ( id )
	 );

	COMMENT ON COLUMN t_complaint.id IS 'Primary Key of the table';

	COMMENT ON COLUMN t_complaint.same_address IS 'flag for if complaint and complainant address is same';

	CREATE TABLE t_document ( 
		id                   bigint  NOT NULL ,
		doc_type_id          bigint  NOT NULL ,
		doc_number           varchar(20)   ,
		file_name            varchar(100)  NOT NULL ,
		file_size            integer  NOT NULL ,
		file_path            varchar(200)  NOT NULL ,
		doc_description      varchar(200)   ,
		doc_owner            varchar(20)  NOT NULL ,
		access_type          varchar(20)  NOT NULL ,
		permission_level     varchar(20)   ,
		created_date         timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL ,
		created_by           varchar(20)  NOT NULL ,
		CONSTRAINT pk_t_document_id PRIMARY KEY ( id )
	 );

	COMMENT ON COLUMN t_document.doc_owner IS 'Username who uploaded document';

	COMMENT ON COLUMN t_document.access_type IS 'Possible values: PRIVATE, PUBLIC, GROUP – Enum in code as single truth';

	COMMENT ON COLUMN t_document.permission_level IS 'Possible values: VIEW, DOWNLOAD, DELETE – Enum in code is single truth';

	CREATE TABLE t_event ( 
		id                   bigint  NOT NULL ,
		event_type_id        bigint  NOT NULL ,
		event_name           varchar(50)  NOT NULL ,
		event_description    varchar(200)   ,
		event_date           timestamp  NOT NULL ,
		organizer_name       varchar(100)  NOT NULL ,
		organizer_phone_no   numeric(10)   ,
		organizer_other_details varchar(200)   ,
		event_location       varchar(200)  NOT NULL ,
		created_date         timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL ,
		created_by           varchar(20)  NOT NULL ,
		CONSTRAINT pk_t_event_id PRIMARY KEY ( id )
	 );

	COMMENT ON COLUMN t_event.id IS 'Primary key of the table';

	CREATE TABLE t_user_address ( 
		id                   bigint  NOT NULL ,
		address1             varchar(200)   ,
		village_id           bigint   ,
		other_village        varchar(50)   ,
		district             varchar(50)   ,
		city                 varchar(50)   ,
		state_id             bigint  NOT NULL ,
		pincode              varchar(6)  NOT NULL ,
		created_date         timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL ,
		created_by           varchar(20)  NOT NULL ,
		CONSTRAINT pk_t_user_address_id PRIMARY KEY ( id )
	 );

	COMMENT ON COLUMN t_user_address.id IS 'Primary key of the table';
	
	CREATE TABLE t_user ( 
		id                   bigint  NOT NULL ,
		username             varchar(50)  NOT NULL ,
		user_password        varchar(200)  NOT NULL ,
		first_name           varchar(100)  NOT NULL ,
		last_name            varchar(100)   ,
		dob                  date  NOT NULL ,
		aadhar_digits        varchar(12)  NOT NULL ,
		address_id           bigint  NOT NULL ,
		occupation           varchar(50)  NOT NULL ,
		mobile_no            varchar(10)  NOT NULL ,
		email_id             varchar(50)   ,
		role				 varchar(10)	,
		created_date         timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL ,
		created_by           varchar(20)  NOT NULL ,
		CONSTRAINT pk_t_user_id PRIMARY KEY ( id ),
		CONSTRAINT unq_t_user UNIQUE ( username ) 
	 );

	CREATE TABLE t_user_session ( 
		id                   bigint  NOT NULL ,
		username             varchar(50)  NOT NULL ,
		user_password        varchar(200)  NOT NULL ,
		role				 varchar(10)	,
		logged_in            boolean  NOT NULL ,
		active            	 boolean  NOT NULL DEFAULT true,
		locked            	 boolean  NOT NULL DEFAULT false,
		cred_expired         boolean  NOT NULL DEFAULT false,		
		last_login           timestamp   ,
		login_time           timestamp   ,
		created_date         timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL ,
		CONSTRAINT pk_t_user_session_id PRIMARY KEY ( id ),
		CONSTRAINT unq_t_user_session UNIQUE ( username )
	 );

	COMMENT ON TABLE t_user_session IS 'Primary key of the table';
	
	-------------------------INDEX AND CONSTRAINT CREATION---------------------------

	DROP INDEX IF EXISTS idx_t_user_address_id;
	CREATE INDEX idx_t_user_address_id ON t_user ( address_id );

	ALTER TABLE t_complaint DROP CONSTRAINT IF EXISTS fk_t_complaint_m_complaint_type;
	ALTER TABLE t_complaint ADD CONSTRAINT fk_t_complaint_m_complaint_type FOREIGN KEY ( complaint_type_id ) REFERENCES m_complaint_type( id );

	ALTER TABLE t_complaint DROP CONSTRAINT IF EXISTS fk_t_complaint_cmplnt_m_village;
	ALTER TABLE t_complaint ADD CONSTRAINT fk_t_complaint_cmplnt_m_village FOREIGN KEY ( complaint_village_id ) REFERENCES m_village( id );

	ALTER TABLE t_complaint DROP CONSTRAINT IF EXISTS fk_t_complaint_t_document;
	ALTER TABLE t_complaint ADD CONSTRAINT fk_t_complaint_t_document FOREIGN KEY ( complaint_doc_id ) REFERENCES t_document( id ) ON DELETE CASCADE;

	ALTER TABLE t_complaint DROP CONSTRAINT IF EXISTS fk_t_complaint_cmplnant_m_village;
	ALTER TABLE t_complaint ADD CONSTRAINT fk_t_complaint_cmplnant_m_village FOREIGN KEY ( complainant_village_id ) REFERENCES m_village( id );

	ALTER TABLE t_document DROP CONSTRAINT IF EXISTS fk_t_document_m_doc_type;
	ALTER TABLE t_document ADD CONSTRAINT fk_t_document_m_doc_type FOREIGN KEY ( doc_type_id ) REFERENCES m_doc_type( id );

	ALTER TABLE t_event DROP CONSTRAINT IF EXISTS fk_t_event_m_event_type;
	ALTER TABLE t_event ADD CONSTRAINT fk_t_event_m_event_type FOREIGN KEY ( event_type_id ) REFERENCES m_event_type( id );

	ALTER TABLE t_user DROP CONSTRAINT IF EXISTS fk_t_user_t_user_address;
	ALTER TABLE t_user ADD CONSTRAINT fk_t_user_t_user_address FOREIGN KEY ( address_id ) REFERENCES t_user_address( id ) ON DELETE CASCADE;

	ALTER TABLE t_user_address DROP CONSTRAINT IF EXISTS fk_t_user_address_m_village;
	ALTER TABLE t_user_address ADD CONSTRAINT fk_t_user_address_m_village FOREIGN KEY ( village_id ) REFERENCES m_village( id );

	ALTER TABLE t_user_address DROP CONSTRAINT IF EXISTS fk_t_user_address_m_state;
	ALTER TABLE t_user_address ADD CONSTRAINT fk_t_user_address_m_state FOREIGN KEY ( state_id ) REFERENCES m_state( id );
	
	ALTER TABLE m_village DROP CONSTRAINT IF EXISTS fk_m_village_m_state;
	ALTER TABLE m_village ADD CONSTRAINT fk_m_village_m_state FOREIGN KEY ( state_id ) REFERENCES m_state( id );

  COMMIT;