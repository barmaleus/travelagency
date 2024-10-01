Travel Agency project on Spring.

Using technologies: Postgres, Maven, HikariCP, Jenkins, etc.



Up Postgres using docker-compose:
`docker-compose -f docker-compose.yml up`

Get into postgres console:
`docker exec -it resources-db-1 bash` (may be different container name instead of `resources-db-1`)

Get into psql console: `psql -h localhost -p 5432 -U postgres -d <database name>`
