FROM java:8
COPY . /var/net/SimpleHttpServer
WORKDIR /var/net/SimpleHttpServer
RUN javac ./src/net/*.java
RUN javac src/Main.java
CMD ["java", "Main"]