-- User: fruser
-- DROP USER fruser;

CREATE USER fruser WITH PASSWORD 'family' LOGIN;
GRANT ALL PRIVILEGES ON DATABASE "familyReserve" to fruser;
