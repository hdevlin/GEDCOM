JFLAGS = -d .
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	date.java  \
	person.java \
	family.java \
	project3.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) MyPackage/*.class