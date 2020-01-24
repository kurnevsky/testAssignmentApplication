package com.eg.assignment.checker.service.test

import com.eg.assignment._

case class MostEdgesTestCase(graph: Set[Edge]) {
  override def toString: String = s"Test case with ${graph.size} edges"
}
