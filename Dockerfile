FROM java:8
COPY . /var/net/SimpleHttpServer
WORKDIR /var/net/SimpleHttpServer
RUN javac Main.java
RUN javac ./net/SimpleHttpServer.java
RUN javac ./net/GetHandler.java
CMD ["java", "Main"]