package Hierarchical.Agglomerative.clustering.types
import Hierarchical.Agglomerative.{Cluster, Point}


/*
  Difference between SingleLinkage and CompleteLinkage is the following:
    1. for SingleLinkage it matters the shortest distance between the closest 2 points of 2 given clusters
    2. for CompleteLinkage, it matters the shortest distance between the 2 points that are the farthest away from
      each other of 2 given clusters
 */

case object CompleteLinkage extends Method {
  override def formCluster(clusters: List[Cluster]): (Cluster, Cluster, Double) = {
    def shortestDistance(current: Cluster, other: Cluster): Double = {
      def distanceBetweenPoints(current: Point, other: Point): Double = {
        Math.sqrt(Math.pow(current.X - other.Y, 2) + Math.pow(current.Y - other.Y, 2))
      }

      val distancesBetweenAllPoints = for {
        currentPoint <- current.points
        otherPoint <- other.points
      } yield distanceBetweenPoints(currentPoint, otherPoint)

      distancesBetweenAllPoints.max
    }

    val shortestDistancesBetweenClusters = for {
      currentCluster <- clusters
      otherCluster <- clusters.filterNot(_ == currentCluster)
    } yield (currentCluster, otherCluster, shortestDistance(currentCluster, otherCluster))

    shortestDistancesBetweenClusters.minBy(_._3)
  }
}
