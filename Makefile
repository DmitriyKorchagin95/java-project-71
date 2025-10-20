# Makefile
run-dist: # run app
	cd app  && ./gradlew clean installDist
	./app/build/install/app/bin/app -f plain app/src/main/resources/file1.yml app/src/main/resources/file2.yml

build: # build app
	cd app  && ./gradlew clean build

.PHONY: build