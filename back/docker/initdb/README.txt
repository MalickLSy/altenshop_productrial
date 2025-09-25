Place .sql files here if you want to run initialization scripts at first DB start.
Example to create a 'postgres' role to silence external clients:
  CREATE ROLE postgres WITH LOGIN PASSWORD 'postgres';
