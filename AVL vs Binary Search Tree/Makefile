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
${BINDIR}/PowerBSTApp.class: ${BINDIR}/PowerAVLApp.class ${BINDIR}/PowerUsage.class ${BINDIR}/BinarySearchTree.class ${SRCDIR}/PowerBSTApp.java

# second build rule
${BINDIR}/PowerAVLApp.class: ${BINDIR}/PowerUsage.class ${BINDIR}/AVLTree.class ${SRCDIR}/PowerAVLApp.java

# third build rule
${BINDIR}/BinarySearchTree.class: ${BINDIR}/BinaryTreeNode.class ${SRCDIR}/BinarySearchTree.java

# fourth build rule
${BINDIR}/AVLTree.class: ${BINDIR}/AVLTreeNode.class ${SRCDIR}/AVLTree.java


clean:
	rm -f ${BINDIR}/*.class

run:
	java -cp ./bin C

docs:
	javadoc  -classpath ${BINDIR} -d ${DOCDIR} ${SRCDIR}/*.java

cleandocs:
	rm -rf ${DOCDIR}/*


