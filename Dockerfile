FROM java:8
COPY . $PWD
WORKDIR .
RUN javac ./src/net/*.java
RUN javac ./src/Main.java
CMD ["java", "Main"]