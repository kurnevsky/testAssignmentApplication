import com.eg.assignment._

class MostEdgesSolution extends MostEdges {
  /**
    * Find the vertex in the graph with the highest sum of incoming and outgoing edges.
    */
  override def findVertexWithMostEdges(graph: Set[Edge]): Option[Vertex] = {
    graph.headOption.map(_.from) // we just pick the first one
  }
}
