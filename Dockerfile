FROM java:8
COPY . /var/net/SimpleHttpServer
WORKDIR /var/net/SimpleHttpServer
RUN javac src/Main.java
RUN javac ./src/net/SimpleHttpServer.java
RUN javac ./src/net/GetHandler.java
CMD ["java", "Main"]