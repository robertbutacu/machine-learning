package bayes.classifier.data

case class Dataset(attribute: Attribute, data: List[Data])

case class ClassAttribute(attribute: Attribute, data: List[Boolean])

case class Input(data: Set[(Attribute, Data)])

/**
  *
  * @param attribute     - attribute represented by the value of the dataset column
  * @param probabilities - a set of data value( value from the table ), outcome for that, and a probability for that
  *                      to happen
  *                      Example:
  *                      Sunny     True
  *                      Sunny     True
  *                      Sunny     False
  *                      Overcast  False
  *                      Overcast  False
  *                      Will be:
    *                    Set(
  *                       (Sunny, True, 2/3),
  *                       (Sunny, False, 1/3),
  *                       (Overcast, False, 1)
  *                      )
  */
case class IndividualProbability(attribute: Attribute, probabilities: Set[(Data, Boolean, Double)])