package Hierarchical.Agglomerative.clustering.dimensions

trait Distance[A, P[_], D <: DistanceType] {
  def computeDistance(A: P[A], B: P[A], distanceType: D): A
}

object Distance {
  implicit def euclidean1D: Distance[Double, UnidimensionalPoint, EuclideanDistance.type] =
    (A: UnidimensionalPoint[Double], B: UnidimensionalPoint[Double], _: EuclideanDistance.type) => {
      // | |X| - |Y| |
      Math.abs(Math.abs(A.X) - Math.abs(B.X))
    }

  implicit def euclidean2D: Distance[Double, BidimensionalPoint, EuclideanDistance.type] =
    (A: BidimensionalPoint[Double], B: BidimensionalPoint[Double], _: EuclideanDistance.type) => {
      Math.sqrt(Math.pow(A.X - B.X, 2) + Math.pow(A.Y - B.Y, 2))
    }

  implicit def euclidean3D: Distance[Double, TridimensionalPoint, EuclideanDistance.type] =
    (A: TridimensionalPoint[Double], B: TridimensionalPoint[Double], _: EuclideanDistance.type) => {
      implicitly[Numeric[Double]].zero
    }

  implicit def manhattan1D: Distance[Double, UnidimensionalPoint, ManhattanDistance.type] =
    (A: UnidimensionalPoint[Double], B: UnidimensionalPoint[Double], _: ManhattanDistance.type) => {
      implicitly[Numeric[Double]].zero
    }

  implicit def manhattan2D: Distance[Double, BidimensionalPoint, ManhattanDistance.type] =
    (A: BidimensionalPoint[Double], B: BidimensionalPoint[Double], _: ManhattanDistance.type) => {
      Math.abs(A.X - B.X) + Math.abs(A.Y - B.Y)
    }

  implicit def manhattan3D: Distance[Double, TridimensionalPoint, ManhattanDistance.type] =
    (A: TridimensionalPoint[Double], B: TridimensionalPoint[Double], _: ManhattanDistance.type) => {
      implicitly[Numeric[Double]].zero
    }

  implicit def chebyshev1D: Distance[Double, UnidimensionalPoint, ChebyshevDistance.type] =
    (A: UnidimensionalPoint[Double], B: UnidimensionalPoint[Double], _: ChebyshevDistance.type) => {
      implicitly[Numeric[Double]].zero
    }

  implicit def chebyshev2D: Distance[Double, BidimensionalPoint, ChebyshevDistance.type] =
    (A: BidimensionalPoint[Double], B: BidimensionalPoint[Double], _: ChebyshevDistance.type) => {
      Math.abs(A.X - B.X)
    }

  implicit def chebyshev3D[Double]: Distance[Double, TridimensionalPoint, ChebyshevDistance.type] =
    (A: TridimensionalPoint[Double], B: TridimensionalPoint[Double], _: ChebyshevDistance.type) => {
      implicitly[Numeric[Double]].zero
    }
}