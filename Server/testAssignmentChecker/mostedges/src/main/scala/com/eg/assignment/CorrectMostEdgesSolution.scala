package com.eg.assignment

object CorrectMostEdgesSolution extends MostEdges {
  def edgeCount(graph: Set[Edge], vertex: Vertex): Int = {
    graph count { edge =>
      edge.from == vertex || edge.to == vertex
    }
  }

  override def findVertexWithMostEdges(graph: Set[Edge]): Vertex = {
    def add(map: Map[Vertex, Int], vertex: Vertex): Map[Vertex, Int] = {
      map + (vertex -> (map.getOrElse(vertex, 0) + 1))
    }

    val counts = graph.foldLeft(Map[Vertex, Int]()) { (acc: Map[Vertex, Int], edge: Edge) =>
      add(add(acc, edge.from), edge.to)
    }

    val (vertex, _) = counts.maxBy { case (_, count) =>
      count
    }

    vertex
  }
}
