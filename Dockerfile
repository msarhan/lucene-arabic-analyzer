FROM openjdk:11.0
COPY target/*.jar /tmp
COPY Tester.java /tmp

WORKDIR /tmp

CMD ["java", "-cp", "lucene-arabic-analyzer-2.0.1.jar", "Tester.java"]
