# Makefile
run-dist: # run app
	cd app  && ./gradlew clean installDist
	./app/build/install/app/bin/app app/src/main/resources/file1.json app/src/main/resources/file1.json

build: # build app
	cd app  && ./gradlew clean build

.PHONY: build