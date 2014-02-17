CC=javac

JDOC = javadoc -encoding utf8 -docencoding utf8 -charset utf8
JDOCFLAGS = -author

MULTI_SERVER_THREAD=MultiThreadServer.java 

SERVER_THREAD=ServerThread.java
GUI=Gui.java

all: classes

default: classes

classes: 
	$(CC) $(MULTI_SERVER_THREAD) $(SERVER_THREAD) $(GUI)

doc:
	mkdir -p doc
	$(JDOC) $(JDOCFLAGS) -d doc *.java

clean:
	rm -f *.class
	rm -fr doc