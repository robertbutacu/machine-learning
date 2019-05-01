package data.hierarchical.clustering.distance

import common.data.{BidimensionalPoint, TridimensionalPoint, UnidimensionalPoint}

import scala.language.higherKinds

trait Distance[A, P[_], D] {
  def computeDistance(from: P[A], to: P[A]): A
}

object Distance {

  /**
    Euclidean distance is represented by the formula:
      Sqrt of sum by i ( Math.pow(Ai - Bi, 2))
   */

  implicit def euclidean1D: Distance[Double, UnidimensionalPoint, EuclideanDistance.type] =
    (from: UnidimensionalPoint[Double], to: UnidimensionalPoint[Double]) => {
      Math.sqrt(Math.pow(from.X - to.X, 2))
    }

  implicit def euclidean2D: Distance[Double, BidimensionalPoint, EuclideanDistance.type] =
    (from: BidimensionalPoint[Double], to: BidimensionalPoint[Double]) => {
      Math.sqrt(Math.pow(from.X - to.X, 2) + Math.pow(from.Y - to.Y, 2))
    }

  implicit def euclidean3D: Distance[Double, TridimensionalPoint, EuclideanDistance.type] =
    (from: TridimensionalPoint[Double], to: TridimensionalPoint[Double]) => {
      Math.sqrt(Math.pow(from.X - to.X, 2) + Math.pow(from.Y - to.Y, 2) + Math.pow(from.Z - to.Z, 2))
    }

  /**
    Manhattan distance is given by the formula:
    Sum by i in Abs(Ai - Bi)
    */

  implicit def manhattan1D: Distance[Double, UnidimensionalPoint, ManhattanDistance.type] =
    (from: UnidimensionalPoint[Double], to: UnidimensionalPoint[Double]) => {
      Math.abs(from.X - to.X)
    }

  implicit def manhattan2D: Distance[Double, BidimensionalPoint, ManhattanDistance.type] =
    (from: BidimensionalPoint[Double], to: BidimensionalPoint[Double]) => {
      Math.abs(from.X - to.X) + Math.abs(from.Y - to.Y)
    }

  implicit def manhattan3D: Distance[Double, TridimensionalPoint, ManhattanDistance.type] =
    (from: TridimensionalPoint[Double], to: TridimensionalPoint[Double]) => {
      Math.abs(from.X - to.Y) + Math.abs(from.Y - to.Y) + Math.abs(from.Z - to.Z)
    }

  /**
    Chebyshev distance is given by the formula:
      Max from | Ai - Bi |
    */

  implicit def chebyshev1D: Distance[Double, UnidimensionalPoint, ChebyshevDistance.type] =
    (from: UnidimensionalPoint[Double], to: UnidimensionalPoint[Double]) => {
      Math.abs(from.X - to.X)
    }

  implicit def chebyshev2D: Distance[Double, BidimensionalPoint, ChebyshevDistance.type] =
    (from: BidimensionalPoint[Double], to: BidimensionalPoint[Double]) => {
      Math.max(Math.abs(from.X - to.X), Math.abs(from.Y - to.Y))
    }

  implicit def chebyshev3D: Distance[Double, TridimensionalPoint, ChebyshevDistance.type] =
    (from: TridimensionalPoint[Double], to: TridimensionalPoint[Double]) => {
      List(Math.abs(from.X - to.X), Math.abs(from.Y - to.Y), Math.abs(from.Z - to.Z)).max
    }
}