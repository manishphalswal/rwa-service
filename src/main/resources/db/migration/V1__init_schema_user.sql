	DROP SEQUENCE IF EXISTS t_user_address_seq;
	DROP SEQUENCE IF EXISTS t_user_seq;
	DROP SEQUENCE IF EXISTS t_user_session_seq;
	
	DROP TABLE IF EXISTS t_user_address CASCADE ;
	DROP TABLE IF EXISTS t_user CASCADE ;
	DROP TABLE IF EXISTS t_user_session CASCADE ;
	
	DROP TABLE IF EXISTS m_state CASCADE ;
	DROP TABLE IF EXISTS m_village CASCADE ;
	
	-------------------------SEQUENCE CREATION---------------------------


	CREATE SEQUENCE t_user_address_seq INCREMENT BY 1 MINVALUE 1;

	COMMENT ON SEQUENCE t_user_address_seq IS 'Sequence to generate primary keys for t_user_address table';

	CREATE SEQUENCE t_user_seq INCREMENT BY 1 MINVALUE 1;

	COMMENT ON SEQUENCE t_user_seq IS 'Sequence to generate primary keys for t_user table';

	CREATE SEQUENCE t_user_session_seq INCREMENT BY 1 MINVALUE 1;

	COMMENT ON SEQUENCE t_user_session_seq IS 'Sequence to generate primary keys for t_user_session table';
	
	-------------------------MASTER TABLE CREATION---------------------------

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
		username             varchar(10)  NOT NULL ,
		user_password        varchar(200)  NOT NULL ,
		first_name           varchar(50)  NOT NULL ,
		last_name            varchar(50)   ,
		dob                  date  NOT NULL ,
		aadhar_digits        varchar(12)  NOT NULL ,
		address_id           bigint  NOT NULL ,
		occupation           varchar(50)  NOT NULL ,
		mobile_no            varchar(10)  NOT NULL ,
		email_id             varchar(50)   ,
		role				 varchar(10) DEFAULT 'BASIC',
		created_date         timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL ,
		created_by           varchar(20)  NOT NULL ,
		CONSTRAINT pk_t_user_id PRIMARY KEY ( id ),
		CONSTRAINT unq_t_user UNIQUE ( username ) 
	 );

	CREATE TABLE t_user_session ( 
		id                   bigint  NOT NULL ,
		username             varchar(10)  NOT NULL ,
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

	ALTER TABLE t_user DROP CONSTRAINT IF EXISTS fk_t_user_t_user_address;
	ALTER TABLE t_user ADD CONSTRAINT fk_t_user_t_user_address FOREIGN KEY ( address_id ) REFERENCES t_user_address( id ) ON DELETE CASCADE;

	ALTER TABLE t_user_address DROP CONSTRAINT IF EXISTS fk_t_user_address_m_village;
	ALTER TABLE t_user_address ADD CONSTRAINT fk_t_user_address_m_village FOREIGN KEY ( village_id ) REFERENCES m_village( id );

	ALTER TABLE t_user_address DROP CONSTRAINT IF EXISTS fk_t_user_address_m_state;
	ALTER TABLE t_user_address ADD CONSTRAINT fk_t_user_address_m_state FOREIGN KEY ( state_id ) REFERENCES m_state( id );
	
	ALTER TABLE m_village DROP CONSTRAINT IF EXISTS fk_m_village_m_state;
	ALTER TABLE m_village ADD CONSTRAINT fk_m_village_m_state FOREIGN KEY ( state_id ) REFERENCES m_state( id );

  COMMIT;