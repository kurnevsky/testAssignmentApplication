package com.eg.assignment

object CorrectMostEdgesSolution {
  def edgeCount(graph: Set[Edge], vertex: Vertex): Int = {
    graph count { edge =>
      edge.from == vertex || edge.to == vertex
    }
  }

  def findVertexWithMostEdges(graph: Set[Edge]): Option[Vertex] = {
    def add(map: Map[Vertex, Int], vertex: Vertex): Map[Vertex, Int] = {
      map + (vertex -> (map.getOrElse(vertex, 0) + 1))
    }

    val counts = graph.foldLeft(Map[Vertex, Int]()) { (acc: Map[Vertex, Int], edge: Edge) =>
      add(add(acc, edge.from), edge.to)
    }

    val (vertex, _) = counts.maxBy { case (_, count) =>
      count
    }

    Some(vertex)
  }
}
