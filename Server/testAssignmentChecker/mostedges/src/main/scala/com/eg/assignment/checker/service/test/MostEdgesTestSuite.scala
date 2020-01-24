package com.eg.assignment.checker.service.test

import cats.implicits._
import com.eg.assignment._
import com.eg.assignment.common.model.result.{TestResult, TestSuiteResult}

class MostEdgesTestSuite(val instance: MostEdges) extends TestSuite[MostEdges] {

  def gradeOne(solution: MostEdges, testCase: MostEdgesTestCase): Either[String, Double] =
    for {
      obtained <- solution.findVertexWithMostEdges(testCase.graph).toRight("Empty solution")
      correct <- CorrectMostEdgesSolution.findVertexWithMostEdges(testCase.graph).toRight("Empty correct solution")
    } yield {
      val obtainedEdgeCount = CorrectMostEdgesSolution.edgeCount(testCase.graph, obtained)
      val correctEdgeCount = CorrectMostEdgesSolution.edgeCount(testCase.graph, correct)

      obtainedEdgeCount.toDouble / correctEdgeCount
    }

  def testCases: List[MostEdgesTestCase] = {
    val cases = List(5, 25, 100, 250, 1000) flatMap { vertexCount =>
      List(0.1d, 0.25d) map { connectionProbability =>
        GraphGenerator.generatePartiallyConnectedDigraph(vertexCount, connectionProbability, 0L)
      }
    }

    cases map MostEdgesTestCase.apply
  }

  def runTestSuite: TestSuiteResult = {
    val results = testCases.map(gradeOne(instance, _))
    def intScore(s: Double) = (s * 1000).toInt
    TestSuiteResult(
      name = "MostEdgesTestSuite",
      isPassed = results.forall(_.isRight),
      resultScore = results.sequence.map(_.sum).map(intScore).toOption,
      hint = None,
      testResults = results.map { r =>
        TestResult(
          isPassed = r.isRight,
          resultScore = r.map(intScore).toOption,
          hint = r.swap.toOption,
        )
      },
    )
  }
}

object MostEdgesTestSuite {
  def apply(instance: MostEdges): MostEdgesTestSuite = new MostEdgesTestSuite(instance)
}
