FROM java:8
COPY . $PWD
WORKDIR $PWD
RUN javac ./src/net/*.java
RUN javac ./src/Main.java
CMD ["java", "Main"]