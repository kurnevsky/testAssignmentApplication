package com.eg.assignment

final case class Vertex(id: String) extends AnyVal

final case class Edge(from: Vertex, to: Vertex, cost: Double)

trait MostEdges {
  def findVertexWithMostEdges(graph: Set[Edge]): Option[Vertex]
}
