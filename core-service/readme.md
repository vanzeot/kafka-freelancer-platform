Compile and run:

1. docker compose build core-service
2. docker compose up -d

Restart with rebuild:
1. docker compose build core-service
2. docker compose up -d core-service

Stop application:

1. docker compose stop

Stop and remove containers (w/ removing volumes):

1. docker compose down
