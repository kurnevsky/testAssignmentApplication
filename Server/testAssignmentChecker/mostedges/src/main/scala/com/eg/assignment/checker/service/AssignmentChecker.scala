package com.eg.assignment.checker.service

import com.eg.assignment.MostEdges
import com.eg.assignment.checker.service.test.MostEdgesTestSuite
import com.eg.assignment.common.model.result.TestSuiteResult
import com.eg.assignment.common.service.Checker

class AssignmentChecker(val instance: MostEdges)
  extends Checker {

  private val testSuites = List(
    MostEdgesTestSuite(instance),
  )

  override lazy val getTestResults: List[TestSuiteResult] =
    testSuites.map(_.runTestSuite)
}

object AssignmentChecker {
  def apply(instance: MostEdges): AssignmentChecker = new AssignmentChecker(instance)
}
