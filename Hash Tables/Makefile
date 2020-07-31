# Simple Makefile to compile PowerUsage.java, PowerHash.java, linearHash.java, quadraticHash.java, chainingHash.java 

BINDIR=./bin
SRCDIR=./src
DOCDIR=./javadocs


.SUFFIXES: .java .class

# General build rule: .java => .class
# This allows dependencies in different directories
# -cp => specified CLASSPATH
# -d  => where to write .class files

${BINDIR}/%.class: ${SRCDIR}/%.java
	javac $< -cp ${BINDIR} -d ${BINDIR} 

# first build rule
${BINDIR}/PowerHash.class: ${BINDIR}/chainingHash.class  ${BINDIR}/quadraticHash.class ${BINDIR}/linearHash.class ${BINDIR}/PowerUsage.class ${SRCDIR}/PowerHash.java

# second build rule
${BINDIR}/linearHash.class: ${BINDIR}/PowerUsage.class ${SRCDIR}/linearHash.java

# third build rule
${BINDIR}/quadraticHash.class: ${BINDIR}/PowerUsage.class ${SRCDIR}/quadraticHash.java

# fourth build rule
${BINDIR}/chainingHash.class:  ${BINDIR}/PowerUsage.class ${SRCDIR}/chainingHash.java

clean:
	rm -f ${BINDIR}/*.class

run:
	java -cp ./bin C

docs:
	javadoc  -classpath ${BINDIR} -d ${DOCDIR} ${SRCDIR}/*.java

cleandocs:
	rm -rf ${DOCDIR}/*


