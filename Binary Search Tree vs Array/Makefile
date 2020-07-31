# Simple Makefile to compile PowerUsage.java, PowerArrayApp.java, PowerBTSApp.java, BinaryTreeNode.java, BinarySearchTree.java with main()

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
${BINDIR}/PowerBTSApp.class: ${BINDIR}/PowerUsage.class ${BINDIR}/BinarySearchTree.class ${SRCDIR}/PowerBTSApp.java

# second build rule
${BINDIR}/PowerArrayApp.class: ${BINDIR}/PowerUsage.class ${SRCDIR}/PowerArrayApp.java

# third build rule
${BINDIR}/BinarySearchTree.class: ${BINDIR}/BinaryTreeNode.class ${SRCDIR}/BinarySearchTree.java


clean:
	rm -f ${BINDIR}/*.class

run:
	java -cp ./bin C

docs:
	javadoc  -classpath ${BINDIR} -d ${DOCDIR} ${SRCDIR}/*.java

cleandocs:
	rm -rf ${DOCDIR}/*


