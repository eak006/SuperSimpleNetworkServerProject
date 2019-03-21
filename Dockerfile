FROM java:8
COPY . /var/net/SimpleHttpServer
WORKDIR /var/net/SimpleHttpServer
RUN javac ./src/net/GetHandler.java
RUN javac ./src/net/SimpleHttpServer.java
RUN javac src/Main.java
CMD ["java", "Main"]