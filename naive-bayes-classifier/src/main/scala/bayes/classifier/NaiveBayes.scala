package bayes.classifier

import bayes.classifier.data._


/**
  * Pro and Cons of Naive Bayes:
  * Pro:
  *       - easy and fast to predict a class of test data set
  *       - performs better compared to other models assuming independence
  *
  * Cons:
  *       - doesn't work on data not present in the training set
  *           - can use other techniques to avoid this
  *       - known as a bad estimator
  *       - independent predictor assumption ( hard to achieve irl )
  */

/**
  *  1. How many positive/negative in conclusion -> probability.
  *  2. Individual probability for each attribute from the dataset -> count and probability
  *  3. Given new data to classify, compute P(X | Conclusion = Positive )
  * P(X | Conclusion = Negative )
  *  4. Divide both sides by evidence P(X) to normalize.
  * P(X) = Product Of Probability Of An Attribute to be equal to the Input Data's Attribute Value.
  */
object NaiveBayes {
  def apply(trainingData: List[Dataset], toClassify: List[Input], classData: ClassAttribute): List[(Dataset, Attribute)] = {
    val classClassified = classDataClassifier(classData)

    val individualProbabilities = trainingData.map(t => trainingDataClassifier(t, classData))

    val positiveOutcome = toClassify.map(c => giveData(c, individualProbabilities, isHappening = true))

    val negativeOutcome = toClassify.map(c => giveData(c, individualProbabilities, isHappening = false))

    println(positiveOutcome)
    println(negativeOutcome)

    List.empty
  }

  private def classDataClassifier(classData: ClassAttribute): List[(Boolean, Double)] =
    classData.data
      .distinct
      .map(d => (d, (classData.data.count(_ == d).toDouble / classData.data.length * 1000).floor / 1000))

  private def trainingDataClassifier(trainingData: Dataset, classData: ClassAttribute): IndividualProbability = {
    val combinedColumns = trainingData.data.zip(classData.data)

    val probabilities = combinedColumns.distinct
      .map(c => (c._1, c._2,
        (combinedColumns.count(_ == c).toDouble / classData.data.count(_ == c._2).toDouble * 1000).floor / 1000))
      .toSet

    IndividualProbability(trainingData.attribute, probabilities)
  }

  /**
    * Input is of type (Attribute, Data) =>
    *     going through individualProbabilities, only those fields where the data and the outcome match
    *       are filtered.
    *
    * @param input                   - data for which it is wanted to find the probability of the output
    * @param individualProbabilities - every attribute's probability for each data
    * @param isHappening             - true/false depending on the wanted output
    * @return
    */
  private def giveData(input: Input,
                       individualProbabilities: List[IndividualProbability],
                       isHappening: Boolean): List[Double] =
    input.data.flatMap(i =>
      individualProbabilities
        .flatMap(d =>
          d.probabilities
            .filter(e => e._1 == i._2 && e._2 == isHappening)
            .toList
        )
    ).toList.map(_._3)

}
