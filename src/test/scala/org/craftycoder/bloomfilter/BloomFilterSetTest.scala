package org.craftycoder.bloomfilter

import org.scalacheck._
import org.scalatest.prop.PropertyChecks
import org.scalatest.{Matchers, WordSpec}

class BloomFilterSetTest extends WordSpec with Matchers with PropertyChecks {

  "BloomFilterSet" should {

    val set = new BloomFilterSet

    "Not Contain any word if no words have been put" in {

      val inputWord: Gen[String] = Gen.alphaNumStr

      forAll(inputWord) { word =>
        set.contains(word) shouldBe false
      }

    }
  }

}
