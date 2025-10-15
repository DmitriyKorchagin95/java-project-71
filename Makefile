# Makefile
run-dist: # run app
	cd app  && ./gradlew clean installDist
	./app/build/install/app/bin/app app/src/test/resources/empty.json app/src/test/resources/empty.json

build: # build app
	cd app  && ./gradlew clean build

.PHONY: build