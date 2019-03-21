FROM java:8
COPY . $PWD
WORKDIR $PWD/src
RUN javac $PWD/src/net/*.java
RUN javac $PWD/src/Main.java
CMD ["java", "Main"]