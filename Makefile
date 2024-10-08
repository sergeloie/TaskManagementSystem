run-dist:
	@./build/install/app/bin/app

lint:
	@./gradlew checkstyleMain checkstyleTest

test:
	@./gradlew test

build:
	@./gradlew clean build

report:
	@./gradlew test jacocoTestReport

check:
	@./gradlew check

install:
	@./gradlew installDist


.PHONY: build