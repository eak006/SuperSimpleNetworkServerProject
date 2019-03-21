FROM java:8
COPY . $PWD
WORKDIR $PWD/src
RUN javac ./net/*.java
RUN javac Main.java
CMD ["java", "Main"]