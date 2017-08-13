package org.craftycoder.bloomfilter

import org.scalatest.{Matchers, WordSpec}

class BloomFilterSetTest extends WordSpec with Matchers {

  "BloomFilterSet" should {

    val set = new BloomFilterSet

    "Not Contain any word if no words have been put" in {

      set.contains("word") shouldBe false

    }
  }

}
