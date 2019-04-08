#!/bin/sh

/usr/local/bin/dockerize \
    -template docker-compose.tmpl.yml:docker-compose.yml
