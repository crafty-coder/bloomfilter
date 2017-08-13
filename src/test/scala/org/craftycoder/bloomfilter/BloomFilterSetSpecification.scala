package org.craftycoder.bloomfilter

import org.scalacheck.{Gen, Properties}
import org.scalacheck.Test.Parameters
import org.scalacheck.Prop.{BooleanOperators, forAll}

object BloomFilterSetSpecification extends Properties("BloomFilterSet") {

  override def overrideParameters(p: Parameters): Parameters =
    p.withMinSuccessfulTests(10000)

  property("Not Contain any word if no words have been put") = forAll {
    (word: String) =>
      !(new BloomFilterSet).contains(word)
  }

  property("Contain a word after is put") = forAll { (word: String) =>
    {
      val set = new BloomFilterSet
      set.put(word)
      set.contains(word)
    }
  }

  //This property could fail in some few cases
  property("Not contain a word if that word is not put before") = {

    val set = new BloomFilterSet
    val usedWords = scala.collection.mutable.SortedSet[String]()

    val wordGen = Gen.alphaNumStr

    for (i <- 1 until 100) {
      wordGen.sample.foreach(word => {
        usedWords += word
        set.put(word)
      })
    }

    forAll { (word: String) =>
      (!usedWords.contains(word)) ==>
        (!set.contains(word))
    }
  }

}
