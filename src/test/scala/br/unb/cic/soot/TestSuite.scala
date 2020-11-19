package br.unb.cic.soot

import br.unb.cic.soot.basic.Basic11Test
import br.unb.cic.soot.svfa.SVFAInterProcedural
import org.scalatest.{BeforeAndAfter, FunSuite}
import test.factory.DefinitionFactory

class TestSuite extends FunSuite with BeforeAndAfter {

  val CLASS_NAME_INTERPROCEDURAL: String = "samples.OverridingAssignmentInterProceduralSample"

  test("test overriding assignment SVFA Analysis InterProcedural") {
    val definition = DefinitionFactory.definition(CLASS_NAME_INTERPROCEDURAL, Array[Int](9), Array[Int](19))
    val cp = "target/scala-2.12/test-classes"

    val svfa = new SVFAInterProcedural(cp, definition)
    svfa.buildSparseValueFlowGraph()

    // for(List<Node> paths: analysis.findSourceSinkPaths()) {
    //  System.out.println(String.join("-> " , paths.stream().map(n -> n.toString()).collect(Collectors.toList())));
    // }

    svfa.findSourceSinkPaths().forEach(paths => {
      paths.forEach(n => println(n.toString))
    })

    assert(svfa.reportConflicts().size == 1)
  }

  test("we should find exactly three conflicts in this analysis") {
    val svfa = new ArrayTest()
    svfa.buildSparseValueFlowGraph()
    assert(svfa.reportConflicts().size == 3)
  }

  test("we should correctly compute the number of nodes and edges in the BlackBoardTest sample") {
    val svfa = new BlackBoardTest()
    svfa.buildSparseValueFlowGraph()
    assert(svfa.svg.nodes.size == 10)
    assert(svfa.svg.numberOfEdges() == 11)
  }

  test("we should not find any conflict in the BlackBoardTest sample") {
    val svfa = new BlackBoardTest()
    svfa.buildSparseValueFlowGraph()
    assert(svfa.reportConflicts().size == 0)
  }

  test("we should correctly compute the number of nodes and edges of the CC16Test sample") {
    val svfa = new CC16Test()
    svfa.buildSparseValueFlowGraph()
    assert(svfa.svg.nodes.size == 13)
    assert(svfa.svg.numberOfEdges() == 14)
  }

  test("we should find exactly one conflict of the CC16Test sample") {
    val svfa = new CC16Test()
    svfa.buildSparseValueFlowGraph()
    assert(svfa.reportConflicts().size == 1)
  }

  test("we should correctly compute the number of nodes and edges of the IfElseTest sample") {
    val svfa = new IfElseTest()
    svfa.buildSparseValueFlowGraph()
    assert(svfa.svg.nodes.size == 17)
  }

  test("we should correctly compute the number of edges of the IfElseTest sample") {
    val svfa = new IfElseTest()
    svfa.buildSparseValueFlowGraph()
    assert(svfa.svg.numberOfEdges() == 18)
  }

  test("we should find exactly one conflict in this analysis of the IfElseTest sample") {
    val svfa = new IfElseTest()
    svfa.buildSparseValueFlowGraph()
    assert(svfa.reportConflicts().size == 1)
  }

  test("we should find two conflicts in the LogbackSampleTest analysis") {
    val svfa = new LogbackSampleTest()
    svfa.buildSparseValueFlowGraph()
    assert(svfa.reportConflicts().size == 3)
  }

  test("we should find exactly one conflict in the StringBuggerTest analysis") {
    val svfa = new StringBufferTest()
    svfa.buildSparseValueFlowGraph()
    assert(svfa.reportConflicts().size == 1)
  }

  test("we should find exactly one conflict in the InitStringBuggerTest analysis") {
    val svfa = new InitStringBufferTest()
    svfa.buildSparseValueFlowGraph()
    assert(svfa.reportConflicts().size == 1)
  }

  test("we should find exactly one conflict in the StringConcatTest analysis") {
    val svfa = new StringConcatTest()
    svfa.buildSparseValueFlowGraph()
    assert(svfa.reportConflicts().size == 6)
  }

//  test("we should find exactly one conflict in the StringGetCharsTest analysis") {
//    val svfa = new StringGetCharsTest()
//    svfa.buildSparseValueFlowGraph()
//    System.out.println(svfa.svgToDotModel())
//    assert(svfa.reportConflicts().size == 1)
//  }

  test("we should find exactly one conflict in the StringToStringTest analysis") {
    val svfa = new StringToStringTest()
    svfa.buildSparseValueFlowGraph()
    assert(svfa.reportConflicts().size == 1)
  }

  ignore("we should find exactly two conflicts in the basic.Basic11 analysis") {
    val svfa = new Basic11Test()
    svfa.buildSparseValueFlowGraph()
    System.out.println(svfa.svgToDotModel())
    assert(svfa.reportConflicts().size == 2)
  }

  ignore("we should find exactly one conflict in the ContextSensitiveSample  analysis") {
    val svfa = new ContextSensitiveTest()
    svfa.buildSparseValueFlowGraph()
    System.out.println(svfa.svgToDotModel())
    assert(svfa.reportConflicts().size == 1)
  }

}
